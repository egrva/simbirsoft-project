package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.AuthorDto;
import ru.aegorova.simbirsoftproject.dto.BookDto;
import ru.aegorova.simbirsoftproject.mappers.AuthorMapper;
import ru.aegorova.simbirsoftproject.mappers.BookMapper;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.repositories.AuthorRepository;
import ru.aegorova.simbirsoftproject.repositories.LibraryCardRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;
    private final LibraryCardRepository libraryCardRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository
            , AuthorMapper authorMapper
            , BookMapper bookMapper
            , LibraryCardRepository libraryCardRepository) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
        this.bookMapper = bookMapper;
        this.libraryCardRepository = libraryCardRepository;
    }

    @Override
    public List<AuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream().map(
                author -> authorMapper.toDto(author)
        ).collect(Collectors.toList());
    }

    @Override
    public List<BookDto> getAllBooksByAuthor(Long authorId) {
        return authorRepository.findBooksByAuthor(authorId).stream().map(
                book -> bookMapper.toDto(book)
        ).collect(Collectors.toList());
    }

    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        return authorMapper.toDto(authorRepository.save(authorMapper.toEntity(authorDto)));
    }

    @Override
    public Boolean deleteAuthor(Long authorId) {
        Set<Book> bookSet = authorRepository.findBooksByAuthor(authorId);
        boolean flag = true;
        for (Book book : bookSet) {
            if (libraryCardRepository.existsByBook_Id(book.getId())) {
                flag = false;
                break;
            }
        }
        if (flag) {
            authorRepository.deleteById(authorId);
        }
        return flag;
    }
}
