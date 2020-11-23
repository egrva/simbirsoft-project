package ru.aegorova.simbirsoftproject.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.repositories.AuthorRepository;
import ru.aegorova.simbirsoftproject.repositories.GenreRepository;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class BookMapper extends AbstractMapper<Book, BookDto> {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    public BookMapper() {
        super(Book.class, BookDto.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Book.class, BookDto.class)
                .addMappings(m -> m.skip(BookDto::setAuthorId))
                .addMappings(m -> m.skip(BookDto::setGenreIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(BookDto.class, Book.class)
                .addMappings(m -> m.skip(Book::setAuthor))
                .addMappings(m -> m.skip(Book::setGenres))
                .setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Book source, BookDto destination) {
        destination.setAuthorId(source.getAuthor().getId());
        destination.setGenreIds(source.getGenres().stream().map(
                genre -> genre.getId()
        ).collect(Collectors.toSet()));
    }

    @Override
    void mapSpecificFields(BookDto source, Book destination) {
        destination.setAuthor(authorRepository.findById(source.getAuthorId()).orElse(null));
        destination.setGenres(genreRepository.findAllByIdIn(source.getGenreIds()));
    }


}
