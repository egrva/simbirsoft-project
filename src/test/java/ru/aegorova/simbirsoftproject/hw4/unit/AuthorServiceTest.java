package ru.aegorova.simbirsoftproject.hw4.unit;

import org.assertj.core.util.CanIgnoreReturnValue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.mappers.AuthorMapper;
import ru.aegorova.simbirsoftproject.mappers.BookMapper;
import ru.aegorova.simbirsoftproject.models.Author;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.AuthorRepository;
import ru.aegorova.simbirsoftproject.repositories.LibraryCardRepository;
import ru.aegorova.simbirsoftproject.services.AuthorService;
import ru.aegorova.simbirsoftproject.services.AuthorServiceImpl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AuthorServiceTest {

    private AuthorRepository authorRepository;
    private AuthorMapper authorMapper;
    private BookMapper bookMapper;
    private LibraryCardRepository libraryCardRepository;
    private AuthorService authorService;

    @Before
    public void setUp() {

        authorRepository = mock(AuthorRepository.class);
        authorMapper = mock(AuthorMapper.class);
        bookMapper = mock(BookMapper.class);
        libraryCardRepository = mock(LibraryCardRepository.class);
        authorService = new AuthorServiceImpl(authorRepository, authorMapper, bookMapper, libraryCardRepository);

        Author author = new Author();
        author.setFirstName("Anastasiia");
        author.setLastName("Egorova");
        author.setMiddleName("Andreevna");
        author.setId(1L);
        author.setBirthDate(LocalDateTime.of(1999,12,16,13,0));

        AuthorDto authorDto = new AuthorDto();
        authorDto.setMiddleName("Andreevna");
        authorDto.setLastName("Egorova");
        authorDto.setFirstName("Anastasiia");
        authorDto.setBirthDate(LocalDateTime.of(1999,12,16,13,0));
        authorDto.setId(1L);

        Book book = new Book();
        book.setAuthor(author);
        book.setName("bookName");
        book.setId(1L);

        BookDto bookDto = new BookDto();
        bookDto.setAuthorId(authorDto.getId());
        bookDto.setName("bookName");
        bookDto.setId(1L);

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

        bookDto.setGenreIds(new HashSet<Long>(){{add(1L);}});
        authorDto.setBooks(new HashSet<BookDto>(){{add(bookDto);}});

        Mockito.when(authorRepository.findAll()).thenReturn(Collections.singletonList(author));
        Mockito.when(authorRepository.findBooksByAuthor(Mockito.anyLong())).thenReturn(books);
        Mockito.when(authorRepository.save(Mockito.any(Author.class))).thenReturn(author);
        Mockito.when(authorMapper.toDto(Mockito.any(Author.class))).thenReturn(authorDto);
        Mockito.when(authorMapper.toEntity(Mockito.any(AuthorDto.class))).thenReturn(author);
        Mockito.when(bookMapper.toDto(Mockito.any(Book.class))).thenReturn(bookDto);
        Mockito.when(bookMapper.toEntity(Mockito.any(BookDto.class))).thenReturn(book);
        Mockito.when(libraryCardRepository.existsByBook_Id(Mockito.anyLong())).thenReturn(true);
    }

    @Test
    public void getAllAuthorsTestPositive() {
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

    @Test
    public void deleteAuthorTest() {
        assertThatThrownBy(() -> {
            authorService.deleteAuthor(1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Вы не можете удалить этого автора, так как его книги " +
                        "все еще у пользователей");
    }
}
