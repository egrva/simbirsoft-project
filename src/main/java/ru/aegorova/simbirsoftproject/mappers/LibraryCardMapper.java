package ru.aegorova.simbirsoftproject.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aegorova.simbirsoftproject.dto.LibraryCardDto;
import ru.aegorova.simbirsoftproject.models.LibraryCard;
import ru.aegorova.simbirsoftproject.repositories.BookRepository;
import ru.aegorova.simbirsoftproject.repositories.PersonRepository;

import javax.annotation.PostConstruct;

@Component
public class LibraryCardMapper extends AbstractMapper<LibraryCard, LibraryCardDto> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    public LibraryCardMapper() {
        super(LibraryCard.class, LibraryCardDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(LibraryCard.class, LibraryCardDto.class)
                .addMappings(m -> m.skip(LibraryCardDto::setBookId))
                .addMappings(m -> m.skip(LibraryCardDto::setPersonId))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(LibraryCardDto.class, LibraryCard.class)
                .addMappings(m -> m.skip(LibraryCard::setBook))
                .addMappings(m -> m.skip(LibraryCard::setPerson))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(LibraryCard source, LibraryCardDto destination) {
        destination.setPersonId(source.getPerson().getId());
        destination.setBookId(source.getBook().getId());
    }

    @Override
    void mapSpecificFields(LibraryCardDto source, LibraryCard destination) {
        destination.setBook(bookRepository.findById(source.getBookId()).orElse(null));
        destination.setPerson(personRepository.findById(source.getPersonId()).orElse(null));
    }
}
