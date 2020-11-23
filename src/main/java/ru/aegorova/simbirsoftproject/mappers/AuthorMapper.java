package ru.aegorova.simbirsoftproject.mappers;

import org.springframework.stereotype.Component;
import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.models.Author;

@Component
public class AuthorMapper extends AbstractMapper<Author, AuthorDto> {

    AuthorMapper() {
        super(Author.class, AuthorDto.class);
    }
}
