package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.services.AuthorService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    //Можно получить список всех авторов.
    @GetMapping("/getAll")
    @JsonView(Views.Public.class)
    public List<AuthorDto> getAllGenres() {
        return authorService.getAllAuthors();
    }

    //Можно получить список книг автора
    @GetMapping("/getBooks/{authorId}")
    @JsonView(Views.Public.class)
    public List<BookDto> getAllBooksByAuthor(@PathVariable Long authorId) {
        return authorService.getAllBooksByAuthor(authorId);
    }

    //Добавить автора
    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
        return authorService.addAuthor(authorDto);
    }

    //Удалить автора (если только нет книг)
    @DeleteMapping("/delete/{id}")
    @JsonView(Views.Internal.class)
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }

    //кастомный поиск
    @GetMapping("/findByFullNameAndCreationDate")
    public List<AuthorDto> getAuthorsByFullNameAndPublicationDate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate from
            , @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate to
            , @RequestParam(required = false) String firstName
            , @RequestParam(required = false) String lastName
            , @RequestParam(required = false) String middleName) {
        return authorService.findByFullNameAndCreationDate(from, to, firstName, lastName, middleName);
    }
}
