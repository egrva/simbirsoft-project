package ru.aegorova.simbirsoftproject.hw4.unit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.mappers.BookMapper;
import ru.aegorova.simbirsoftproject.models.Author;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.BookRepository;
import ru.aegorova.simbirsoftproject.repositories.GenreRepository;
import ru.aegorova.simbirsoftproject.repositories.LibraryCardRepository;
import ru.aegorova.simbirsoftproject.services.BookService;
import ru.aegorova.simbirsoftproject.services.BookServiceImpl;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class BookServiceTest {

    private BookMapper bookMapper;
    private LibraryCardRepository libraryCardRepository;
    private BookService bookService;
    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @Before
    public void setUp() {
        bookMapper = mock(BookMapper.class);
        libraryCardRepository = mock(LibraryCardRepository.class);
        bookRepository = mock(BookRepository.class);
        genreRepository = mock(GenreRepository.class);
        bookService = new BookServiceImpl(bookMapper, bookRepository, genreRepository, libraryCardRepository);

        Author author = new Author();
        author.setFirstName("Anastasiia");
        author.setLastName("Egorova");
        author.setMiddleName("Andreevna");
        author.setId(1L);
        author.setBirthDate(LocalDateTime.of(1999, 12, 16, 13, 0));

        AuthorDto authorDto = new AuthorDto();
        authorDto.setMiddleName("Andreevna");
        authorDto.setLastName("Egorova");
        authorDto.setFirstName("Anastasiia");
        authorDto.setBirthDate(LocalDateTime.of(1999, 12, 16, 13, 0));
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
        Set<Genre> genres = new HashSet<Genre>() {{
            add(genre);
        }};
        book.setGenres(genres);
        Set<Book> books = new HashSet<Book>() {{
            add(book);
        }};
        author.setBooks(books);

        bookDto.setGenreIds(new HashSet<Long>() {{
            add(1L);
        }});
        authorDto.setBooks(new HashSet<BookDto>() {{
            add(bookDto);
        }});

        Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
        Mockito.when(bookRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(book));
        Mockito.when(bookRepository.getOne(Mockito.anyLong())).thenReturn(book);
        Mockito.when(bookRepository.findAll((Example<Book>) Mockito.any())).thenReturn(Collections.singletonList(book));
        Mockito.when(bookMapper.toDto(Mockito.any(Book.class))).thenReturn(bookDto);
        Mockito.when(bookMapper.toEntity(Mockito.any(BookDto.class))).thenReturn(book);
        Mockito.when(libraryCardRepository.existsByBook_Id(Mockito.anyLong())).thenReturn(true);
        Mockito.when(genreRepository.getOne(Mockito.anyLong())).thenReturn(genre);
    }

    @Test
    public void getBookByIdTest() {
        assertThat((bookService.getBookById(1L)).getName().equals("bookName"));
    }

    @Test
    public void addBookTest() {
        BookDto bookDto = new BookDto() {{
            setName("bookName");
        }};
        assertThat((bookService.addBook(bookDto)).size() == 1);
    }

    @Test
    public void deleteBookTest() {
        assertThatThrownBy(() -> {
            bookService.deleteBook(1L);
        }).isInstanceOf(RuntimeException.class)
                .hasMessage("Вы не можете удалить книгу из библиотеки, так как " +
                        "сейчас она находится у пользователя");
    }

    @Test
    public void addOrDeleteGenreTest() {
        assertThat((bookService.addOrDeleteGenre(1L, 2L)).getGenreIds().size() == 2);
    }

    @Test
    public void getBookByAuthorTest() {
        assertThat(bookService.getBookByAuthor("Anastasiia", "Egorova", "Andreevna")
                .size() == 1);
    }

    @Test
    public void getBookByGenreTest() {
        assertThat(bookService.getBookByGenre(1L).size() == 1);
    }
}
