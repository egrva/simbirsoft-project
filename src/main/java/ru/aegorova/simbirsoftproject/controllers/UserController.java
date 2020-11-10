package ru.aegorova.simbirsoftproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.User;
import ru.aegorova.simbirsoftproject.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(User.users);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<User>> getUserByName(@PathVariable String name){
        return ResponseEntity.ok(userService.getUsersByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<List<User>> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String name
            , @RequestParam String surname
            , @RequestParam String patronymic){
        userService.deleteUser(name, surname, patronymic);
        return ResponseEntity.ok().build();
    }
}
