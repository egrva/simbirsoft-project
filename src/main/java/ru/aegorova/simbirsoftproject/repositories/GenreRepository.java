package ru.aegorova.simbirsoftproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.aegorova.simbirsoftproject.models.Genre;

import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Set<Genre> findAllByIdIn(Set<Long> ids);

    @Query("SELECT genre.books.size FROM Genre genre WHERE " +
            "genre.genreName = :genreName")
    int countStatistic(@Param("genreName") String genreName);

}
