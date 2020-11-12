package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.Book;
import ru.aegorova.simbirsoftproject.services.BookService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    @JsonView(Views.Internal.class)
    public List<Book> getAllBooks() {
        return BookService.books;
    }

    @GetMapping("/getByAuthor/{author}")
    public List<Book> getBookByTitle(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }

    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public List<Book> addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBook(@RequestParam String author, @RequestParam String title) {
        bookService.deleteBook(author, title);
        return ResponseEntity.ok().build();
    }

}
