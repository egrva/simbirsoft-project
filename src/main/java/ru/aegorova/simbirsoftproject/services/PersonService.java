package ru.aegorova.simbirsoftproject.services;

import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.dto.PersonDto;

import java.util.List;

public interface PersonService {

    // добавить пользователя
    PersonDto addPerson(PersonDto personDto);

    //Информация о пользователе может быть изменена
    PersonDto updatePerson(PersonDto personDto);

    //Пользователь может быть удалён по ID
    Boolean deleteUserById(Long personId);

    //Пользователь или пользователи могут быть удалены по ФИО
    Boolean deleteUserByFio(String firstName, String lastName, String middleName);

    //Получить список всех взятых пользователем книг
    List<BookDto> getBooksByPersonId(Long personId);

    //Пользователь может взять книгу
    PersonDto addBookToPerson(Long personId, Long bookId);

    //Пользователь может вернуть книгу
    PersonDto deleteBookToPerson(Long personId, Long bookId);
}
