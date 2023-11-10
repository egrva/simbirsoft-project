package ru.aegorova.simbirsoftproject.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    private final PersonRepository personRepository;
    private final LibraryCardRepository libraryCardRepository;
    private final PersonMapper personMapper;
    private final BookMapper bookMapper;
    private final LibraryCardMapper libraryCardMapper;

    public PersonServiceImpl(PersonRepository personRepository
            , LibraryCardRepository libraryCardRepository
            , PersonMapper personMapper
            , BookMapper bookMapper
            , LibraryCardMapper libraryCardMapper) {
        this.personRepository = personRepository;
        this.libraryCardRepository = libraryCardRepository;
        this.personMapper = personMapper;
        this.bookMapper = bookMapper;
        this.libraryCardMapper = libraryCardMapper;
    }

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
    public void deleteUserById(Long personId) {
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalArgumentException("Вы не можете удалить данного пользователя," +
                        "так как его не существует в бд"));
        personRepository.delete(person);
    }

    @Override
    public void deleteUserByFullName(String firstName, String lastName, String middleName) {
        Person person = personRepository.findByFirstNameAndLastNameAndMiddleName(firstName, lastName, middleName)
                .orElseThrow(() -> new IllegalArgumentException("Вы не можете удалить данного пользователя," +
                        "так как его не существует в бд"));
        personRepository.delete(person);
    }

    @Override
    public List<BookDto> getBooksByPersonId(Long personId) {
        return libraryCardRepository.findBooksByPerson(personId)
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
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

    @Override
    public PersonDto getCurrentPerson() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return personRepository.findByUserLogin(((UserDetails) principal).getUsername())
                    .map(personMapper::toDto)
                    .orElseThrow(() -> new IllegalArgumentException("Данного пользователя нет в системе"));
        }
        throw new IllegalArgumentException("Данный пользователь не залогинен");
    }
}
