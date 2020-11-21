package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.services.BookService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.util.Set;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    //Книга может быть добавлена
    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public Set<BookDto> addBook(@RequestBody BookDto book) {
        return bookService.addBook(book);
    }

    //Книга может быть удалена из списка библиотеки (но только если она не у пользователя) по ID
    @DeleteMapping("/delete/{id}")
    @JsonView(Views.Internal.class)
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id))
            return ResponseEntity.ok().build();
        else return ResponseEntity.badRequest().body("Вы не можете удалить книгу из библиотеки, так как " +
                "сейчас она находится у пользователя");
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

}
