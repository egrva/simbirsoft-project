package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    public List<Book> getBooksByAuthor(String author) {
        return books.stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
    }

    public List<Book> addBook(Book book) {
        books.add(book);
        return books;
    }

    public void deleteBook(String author, String title) {
        books.removeIf(book -> (book.getAuthor().equals(author) && book.getTitle().equals(title)));
    }

    public static List<Book> books = new ArrayList<Book>() {{
        add(Book.builder()
                .author("Fyodor Dostoyevski")
                .title("Crime and Punishment")
                .genre("Philosophical novel")
                .build());
        add(Book.builder()
                .author("Ivan Turgenev")
                .title("Fathers and Sons")
                .genre("romance")
                .build());
        add(Book.builder()
                .author("Leo Tolstoy")
                .title("War and Peace")
                .genre("Historical novel")
                .build());
    }};
}
