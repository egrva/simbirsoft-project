package ru.aegorova.simbirsoftproject.services;

import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.utils.BookFilter;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookService {

    //Книга может быть добавлена
    Set<BookDto> addBook(BookDto bookDto);

    //Книга может быть удалена из списка библиотеки
    void deleteBook(Long id);

    // найти книгу по айди
    BookDto getBookById(Long id);

    //Книге можно присвоить новый жанр, или удалить один из имеющихся
    BookDto addOrDeleteGenre(Long bookId, Long genreId);

    //Можно получить список всех книг с фильтром по автору
    Set<BookDto> getBookByAuthor(String firstName, String lastName, String middleNam);

    //Можно получить список книг по жанру
    Set<BookDto> getBookByGenre(Long genreId);

    //кастомный поиск
    List<BookDto> findByGenreAndPublicationDate(Long genreId
            , LocalDate publicationDate
            , String bookFilter);

}

