package ru.aegorova.simbirsoftproject.repositories.custom;

import ru.aegorova.simbirsoftproject.models.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorCustomRepository {
    List<Author> findByFullNameAndCreationDate(LocalDate from
            , LocalDate to
            , String firstName
            , String lastName
            , String middleName);
}
