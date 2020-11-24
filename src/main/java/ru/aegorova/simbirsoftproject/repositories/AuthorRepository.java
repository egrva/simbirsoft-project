package ru.aegorova.simbirsoftproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Author;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.repositories.custom.AuthorCustomRepository;

import java.util.Set;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorCustomRepository {

    @Query("SELECT author.books FROM Author author WHERE author.id = :id")
    Set<Book> findBooksByAuthor(@Param("id") Long id);

}
