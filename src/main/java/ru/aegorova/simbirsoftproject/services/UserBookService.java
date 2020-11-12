package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.UserBook;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookService {

    public List<UserBook> addUserBook(UserBook userBook) {
        userBooks.add(userBook);
        return userBooks;
    }

    public static List<UserBook> userBooks = new ArrayList<>();
}
