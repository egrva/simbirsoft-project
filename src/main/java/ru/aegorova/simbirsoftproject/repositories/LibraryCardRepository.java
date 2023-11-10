package ru.aegorova.simbirsoftproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.LibraryCard;

import java.util.Set;

@Repository
public interface LibraryCardRepository extends JpaRepository<LibraryCard, Long> {
    Boolean existsByBook_Id(Long book_id);

    @Query("SELECT card.book FROM LibraryCard card WHERE card.person.id = :id")
    Set<Book> findBooksByPerson(@Param("id") Long id);

    @Modifying
    @Query("delete from LibraryCard card where card.person.id=:personId " +
            "and card.book.id=:bookId")
    void deleteByBookIdAndPersonId(@Param("personId") Long personId
            , @Param("bookId") Long bookId);

}
