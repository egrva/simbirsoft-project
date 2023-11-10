package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.dto.PersonDto;
import ru.aegorova.simbirsoftproject.services.PersonService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /*
    //Пользователь может быть добавлен.
    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public PersonDto addUser(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }
    //Пользователь может быть удалён по ID
    @DeleteMapping("/deleteById/{id}")
    @JsonView(Views.Public.class)
    public void deleteUserById(@PathVariable Long id) {
        personService.deleteUserById(id);
    }
    //Пользователь или пользователи могут быть удалены по ФИО
    @DeleteMapping("/deleteByFullName")
    @JsonView(Views.Public.class)
    public void deleteUserByFullName(@RequestParam String firstName
            , @RequestParam String lastName
            , @RequestParam String middleName) {
        personService.deleteUserByFullName(firstName, lastName, middleName);
    }
    */

    //Информация о пользователе может быть изменена (POST)
    @PostMapping("/update")
    @JsonView(Views.Public.class)
    public PersonDto updateUser(@RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto);
    }

    //Получить список всех взятых пользователем книг
    @GetMapping("/getBooks")
    @JsonView(Views.Public.class)
    public List<BookDto> getAllBooksByPerson() {
        return personService.getBooksByPersonId(personService.getCurrentPerson().getId());
    }

    //Пользователь может взять книгу
    @PutMapping("/addBook")
    @JsonView(Views.Public.class)
    public PersonDto addBookToPerson(@RequestParam Long bookId) {
        return personService.addBookToPerson(personService.getCurrentPerson().getId(), bookId);
    }

    //Пользователь может вернуть книгу
    @PutMapping("/returnBook")
    @JsonView(Views.Public.class)
    public PersonDto returnBookFromPerson(@RequestParam Long bookId) {
        return personService.deleteBookToPerson(personService.getCurrentPerson().getId(), bookId);
    }
}
