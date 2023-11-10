package ru.aegorova.simbirsoftproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.custom.BookCustomRepository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookCustomRepository {

    List<Book> findAllByGenresContains(Genre genre);

}
