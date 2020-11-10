package ru.aegorova.simbirsoftproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.Book;
import ru.aegorova.simbirsoftproject.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(Book.books);
    }

    @GetMapping("/{author}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));
    }

    @PostMapping("/add")
    public ResponseEntity<List<Book>> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.addBook(book));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBook(@RequestParam String author, @RequestParam String title) {
        bookService.deleteBook(author, title);
        return ResponseEntity.ok().build();
    }

}
