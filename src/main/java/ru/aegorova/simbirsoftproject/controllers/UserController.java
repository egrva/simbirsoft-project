package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.User;
import ru.aegorova.simbirsoftproject.services.UserService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @JsonView(Views.Internal.class)
    public List<User> getAllUsers() {
        return UserService.users;
    }

    @GetMapping("/getByName/{name}")
    public List<User> getUserByName(@PathVariable String name) {
        return userService.getUsersByName(name);
    }

    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public List<User> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String name
            , @RequestParam String surname
            , @RequestParam String patronymic) {
        userService.deleteUser(name, surname, patronymic);
        return ResponseEntity.ok().build();
    }
}
