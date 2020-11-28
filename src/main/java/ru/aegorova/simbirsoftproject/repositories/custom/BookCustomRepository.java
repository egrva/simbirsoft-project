package ru.aegorova.simbirsoftproject.repositories.custom;

import java.time.LocalDate;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.utils.BookFilter;

import java.util.List;

public interface BookCustomRepository {
    List<Book> findByGenreAndPublicationDate(Genre genre
            , LocalDate publicationDate
            , BookFilter bookFilter);
}
