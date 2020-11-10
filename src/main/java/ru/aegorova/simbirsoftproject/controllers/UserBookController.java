package ru.aegorova.simbirsoftproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aegorova.simbirsoftproject.dto.UserBook;
import ru.aegorova.simbirsoftproject.services.UserBookService;

import java.util.List;

@RestController
@RequestMapping("/api/user_books")
public class UserBookController {

    @Autowired
    private UserBookService userBookService;

    @PostMapping("/add")
    public ResponseEntity<List<UserBook>> addUserBook(@RequestBody UserBook userBook) {
        return ResponseEntity.ok(userBookService.addUserBook(userBook));
    }
}
