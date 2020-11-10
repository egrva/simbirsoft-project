package ru.aegorova.simbirsoftproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String title;
    private String author;
    private String genre;

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
