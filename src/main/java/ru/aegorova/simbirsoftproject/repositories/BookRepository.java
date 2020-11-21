package ru.aegorova.simbirsoftproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByGenresContains(Genre genre);

}
