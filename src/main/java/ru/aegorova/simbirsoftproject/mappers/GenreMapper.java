package ru.aegorova.simbirsoftproject.mappers;

import org.springframework.stereotype.Component;
import ru.aegorova.simbirsoftproject.dto.GenreDto;
import ru.aegorova.simbirsoftproject.models.Genre;

@Component
public class GenreMapper extends AbstractMapper<Genre, GenreDto> {
    public GenreMapper() {
        super(Genre.class, GenreDto.class);
    }
}
