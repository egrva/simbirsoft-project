package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.services.BookService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Книга может быть добавлена
    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public Set<BookDto> addBook(@RequestBody BookDto book) {
        return bookService.addBook(book);
    }

    //Книга может быть удалена из списка библиотеки (но только если она не у пользователя) по ID
    @DeleteMapping("/delete/{id}")
    @JsonView(Views.Internal.class)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    //Книге можно присвоить новый жанр, или удалить один из имеющихся
    @PutMapping("/addOrRemoveGenre")
    @JsonView(Views.Internal.class)
    public BookDto addOrRemoveGenre(@RequestParam Long genreId, @RequestParam Long bookId) {
        return bookService.addOrDeleteGenre(bookId, genreId);
    }

    //Можно получить список всех книг с фильтром по автору (По любой комбинации трёх полей сущности автор.
    @GetMapping("/getByAuthor")
    @JsonView(Views.Internal.class)
    public Set<BookDto> getBooksByAuthor(@RequestParam(required = false) String firstName
            , @RequestParam(required = false) String lastName
            , @RequestParam(required = false) String middleName) {
        return bookService.getBookByAuthor(firstName, lastName, middleName);
    }

    //Можно получить список книг по жанру.
    @GetMapping("/getByGenre/{genreId}")
    public Set<BookDto> getBooksByGenre(@PathVariable Long genreId) {
        return bookService.getBookByGenre(genreId);
    }

    //кастомный поиск
    @GetMapping("/findByGenreAndPublicationDate")
    public List<BookDto> getBooksByGenreAndPublicationDate(
            @RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate publicationDate
            , @RequestParam(required = false) Long genreId
            , @RequestParam(required = false) String bookFilter) {
        return bookService.findByGenreAndPublicationDate(genreId, publicationDate, bookFilter);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handle(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
