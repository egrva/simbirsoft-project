package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.UserBook;

import java.util.List;

@Service
public class UserBookService {

    public List<UserBook> addUserBook(UserBook userBook) {
        UserBook.userBooks.add(userBook);
        return UserBook.userBooks;
    }
}
