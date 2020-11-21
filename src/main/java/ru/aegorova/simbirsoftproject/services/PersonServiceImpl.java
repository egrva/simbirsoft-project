package ru.aegorova.simbirsoftproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.dto.LibraryCardDto;
import ru.aegorova.simbirsoftproject.dto.PersonDto;
import ru.aegorova.simbirsoftproject.mappers.BookMapper;
import ru.aegorova.simbirsoftproject.mappers.LibraryCardMapper;
import ru.aegorova.simbirsoftproject.mappers.PersonMapper;
import ru.aegorova.simbirsoftproject.models.Person;
import ru.aegorova.simbirsoftproject.repositories.LibraryCardRepository;
import ru.aegorova.simbirsoftproject.repositories.PersonRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LibraryCardRepository libraryCardRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private LibraryCardMapper libraryCardMapper;

    @Override
    public PersonDto addPerson(PersonDto personDto) {
        System.out.println(personDto.getId());
        return personMapper.toDto(personRepository.save(personMapper.toEntity(personDto)));
    }

    @Override
    public PersonDto updatePerson(PersonDto personDto) {
        Person person = personRepository.getOne(personDto.getId());
        person.setMiddleName(personDto.getMiddleName());
        person.setLastName(personDto.getLastName());
        person.setFirstName(personDto.getFirstName());
        person.setBirthDate(personDto.getBirthDate());
        return personMapper.toDto(personRepository.save(person));
    }

    @Override
    public Boolean deleteUserById(Long personId) {
        if (personRepository.existsById(personId)) {
            personRepository.deleteById(personId);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteUserByFio(String firstName, String lastName, String middleName) {
        Set<Person> persons = personRepository.findPersonByFio(firstName, lastName, middleName);
        if (!persons.isEmpty()) {
            personRepository.deleteAll(persons);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<BookDto> getBooksByPersonId(Long personId) {
        return libraryCardRepository.findBooksByPerson(personId).stream().map(
                book -> bookMapper.toDto(book)
        ).collect(Collectors.toList());
    }

    @Override
    public PersonDto addBookToPerson(Long personId, Long bookId) {
        libraryCardRepository.save(libraryCardMapper.toEntity(new LibraryCardDto(personId, bookId, ZonedDateTime.now())));
        return personMapper.toDto(personRepository.getOne(personId));

    }

    @Override
    @Transactional
    public PersonDto deleteBookToPerson(Long personId, Long bookId) {
        libraryCardRepository.deleteByBookIdAndPersonId(personId, bookId);
        return personMapper.toDto(personRepository.getOne(personId));
    }

}
