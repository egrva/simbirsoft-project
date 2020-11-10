package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.Book;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    public List<Book> getBooksByAuthor(String author){
        return Book.books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Book> addBook(Book book){
        Book.books.add(book);
        return Book.books;
    }

    public void deleteBook(String author, String title){
        Book.books.removeIf(book -> (book.getAuthor().equals(author) && book.getTitle().equals(title)));
    }
}
