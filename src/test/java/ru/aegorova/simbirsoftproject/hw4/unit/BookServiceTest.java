package ru.aegorova.simbirsoftproject.hw4.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.models.Author;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.BookRepository;
import ru.aegorova.simbirsoftproject.services.BookService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @Before
    public void init() {
        Author author = new Author();
        author.setFirstName("Anastasiia");
        author.setLastName("Egorova");
        author.setMiddleName("Andreevna");
        author.setId(1L);
        author.setBirthDate(LocalDateTime.of(1999,12,16,13,0));

        Book book = new Book();
        book.setAuthor(author);
        book.setName("bookName");
        book.setId(1L);

        Genre genre = new Genre();
        genre.setGenreName("genreName");
        Set<Genre> genres = new HashSet<Genre>(){{
            add(genre);
        }};
        book.setGenres(genres);
        Set<Book> books = new HashSet<Book>(){{
            add(book);
        }};
        author.setBooks(books);

        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Mockito.when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
    }

    @Test
    public void getBookByIdTest() {
        assertThat((bookService.getBookById(1L)).getName().equals("bookName"));
    }

    @Test
    public void addBookTest() {
        BookDto bookDto = new BookDto(){{
            setName("bookName");
        }};
        assertThat((bookService.addBook(bookDto)).size() == 1);
    }
}
