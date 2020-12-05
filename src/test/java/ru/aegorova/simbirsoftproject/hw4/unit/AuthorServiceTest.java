package ru.aegorova.simbirsoftproject.hw4.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.models.Author;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.AuthorRepository;
import ru.aegorova.simbirsoftproject.services.AuthorService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @MockBean
    private AuthorRepository authorRepository;

    @Before
    public void setUp() {
        Author author = new Author();
        author.setFirstName("Anastasiia");
        author.setLastName("Egorova");
        author.setMiddleName("Andreevna");
        author.setId(1L);
        author.setBirthDate(LocalDateTime.of(1999,12,16,13,0));

        Book book = new Book();
        book.setAuthor(author);
        book.setName("bookName");

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
        Mockito.when(authorRepository.findAll()).thenReturn(Collections.singletonList(author));
        Mockito.when(authorRepository.findBooksByAuthor(1L)).thenReturn(books);
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
    }

    @Test
    public void getAllAuthorsTest() {
        assertThat((authorService.getAllAuthors()).size() == 1);
        assertThat((authorService.getAllAuthors()).stream().findFirst()
                .get().getFirstName().equals("Anastasiia"));
    }

    @Test
    public void getAllBooksByAuthorTest() {
        assertThat((authorService.getAllBooksByAuthor(1L)).size() == 1);
        assertThat((authorService.getAllBooksByAuthor(1L)).stream().findFirst()
                .get().getName().equals("bookName"));
    }

    @Test
    public void addAuthorTest() {
        AuthorDto authorDto = new AuthorDto() {{
            setFirstName("Anastasiia");
            setLastName("Egorova");
            setMiddleName("Andreevna");
        }};
        assertThat((authorService.addAuthor(authorDto)).getFirstName().equals(authorDto.getFirstName()));
        assertThat((authorService.addAuthor(authorDto)).getLastName().equals(authorDto.getLastName()));
        assertThat((authorService.addAuthor(authorDto)).getMiddleName().equals(authorDto.getMiddleName()));
    }
}
