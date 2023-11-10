package ru.aegorova.simbirsoftproject.services;

import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.dto.BookDto;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    //Можно получить список всех авторов.
    List<AuthorDto> getAllAuthors();

    //Можно получить список книг автора
    List<BookDto> getAllBooksByAuthor(Long authorId);

    //Добавить автора
    AuthorDto addAuthor(AuthorDto authorDto);

    //Удалить автора
    void deleteAuthor(Long authorId);

    //кастомный поиск
    List<AuthorDto> findByFullNameAndCreationDate(LocalDate from
            , LocalDate to
            , String firstName
            , String lastName
            , String middleName);
}
