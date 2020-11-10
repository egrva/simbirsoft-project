package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public List<User> addUser(User user) {
        User.users.add(user);
        return User.users;
    }

    public List<User> deleteUser(String name, String surname, String patronymic) {
        User.users.removeIf(user -> (
                user.getName().equals(name) &&
                        user.getSurname().equals(surname) &&
                        user.getPatronymic().equals(patronymic)));
        return User.users;
    }

    public List<User> getUsersByName(String name) {
        return User.users.stream().filter(user -> user.getName().equals(name)).collect(Collectors.toList());
    }

}
