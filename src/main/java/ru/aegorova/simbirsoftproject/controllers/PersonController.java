package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PersonService personService;

    //Пользователь может быть добавлен.
    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public PersonDto addUser(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    //Информация о пользователе может быть изменена (POST)
    @PostMapping("/update")
    @JsonView(Views.Public.class)
    public PersonDto updateUser(@RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto);
    }

    //Пользователь может быть удалён по ID
    @DeleteMapping("/deleteById/{id}")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        if (personService.deleteUserById(id))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().body("Вы не можете удалить данного пользователя," +
                "так как его не существует в бд");
    }

    //Пользователь или пользователи могут быть удалены по ФИО
    @DeleteMapping("/deleteByFio")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> deleteUserByFio(@RequestParam String firstName
            , @RequestParam String lastName
            , @RequestParam String middleName) {
        if (personService.deleteUserByFio(firstName, lastName, middleName))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().body("Вы не можете удалить данного пользователя," +
                "так как его не существует в бд");
    }

    //Получить список всех взятых пользователем книг
    @GetMapping("/getBooks/{personId}")
    @JsonView(Views.Public.class)
    public List<BookDto> getAllBooksByPerson(@PathVariable Long personId) {
        return personService.getBooksByPersonId(personId);
    }

    //Пользователь может взять книгу
    @PutMapping("/addBook")
    @JsonView(Views.Public.class)
    public PersonDto addBookToPerson(@RequestParam Long personId, @RequestParam Long bookId) {
        return personService.addBookToPerson(personId, bookId);
    }

    //Пользователь может вернуть книгу
    @PutMapping("/returnBook")
    @JsonView(Views.Public.class)
    public PersonDto returnBookFromPerson(@RequestParam Long personId, @RequestParam Long bookId) {
        return personService.deleteBookToPerson(personId, bookId);
    }

}
