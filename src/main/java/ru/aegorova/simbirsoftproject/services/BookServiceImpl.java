package ru.aegorova.simbirsoftproject.services;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.mappers.BookMapper;
import ru.aegorova.simbirsoftproject.models.Author;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.BookRepository;
import ru.aegorova.simbirsoftproject.repositories.GenreRepository;
import ru.aegorova.simbirsoftproject.repositories.LibraryCardRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final LibraryCardRepository libraryCardRepository;

    public BookServiceImpl(BookMapper bookMapper
            , BookRepository bookRepository
            , GenreRepository genreRepository
            , LibraryCardRepository libraryCardRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.libraryCardRepository = libraryCardRepository;
    }

    @Override
    public Set<BookDto> addBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        int i = 0;
        bookRepository.save(book);
        return bookRepository.findAll().stream().map(
                b -> bookMapper.toDto(b)
        ).collect(Collectors.toSet());
    }

    @Override
    public Boolean deleteBook(Long id) {
        if (libraryCardRepository.existsByBook_Id(id)) {
            return false;
        }
        else {
            bookRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public BookDto getBookById(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(book -> bookMapper.toDto(book)).orElse(null);
    }

    @Override
    public BookDto addOrDeleteGenre(Long bookId, Long genreId) {
        Book book = bookRepository.getOne(bookId);
        Genre genre = genreRepository.getOne(genreId);
        if (book.getGenres().contains(genre)) {
            book.getGenres().remove(genre);
        }
        else {
            book.getGenres().add(genre);
        }
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public Set<BookDto> getBookByAuthor(String firstName, String lastName, String middleName) {
        Author author = new Author(firstName, lastName, middleName);
        Book book = new Book();
        book.setAuthor(author);
        Example<Book> bookExample = Example.of(book);
        return bookRepository.findAll(bookExample)
                .stream().map(b -> bookMapper.toDto(b)).collect(Collectors.toSet());
    }

    @Override
    public Set<BookDto> getBookByGenre(Long genreId) {
        return bookRepository.findAllByGenresContains(genreRepository.getOne(genreId))
                .stream().map(book -> bookMapper.toDto(book)).collect(Collectors.toSet());
    }

}
