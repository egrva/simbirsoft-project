package ru.aegorova.simbirsoftproject.services;

import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    public List<User> addUser(User user) {
        users.add(user);
        return users;
    }

    public void deleteUser(String name, String surname, String patronymic) {
        users.removeIf(user -> (
                user.getName().equals(name) &&
                        user.getSurname().equals(surname) &&
                        user.getPatronymic().equals(patronymic)));
    }

    public List<User> getUsersByName(String name) {
        return users.stream().filter(user -> user.getName().equals(name)).collect(Collectors.toList());
    }

    public static List<User> users = new ArrayList<User>() {{
        add(User.builder()
                .name("Nastya")
                .surname("Egorova")
                .patronymic("Andreevna")
                .dateOfBirth(LocalDate.of(1999, 12, 16))
                .build());
        add(User.builder()
                .name("Katya")
                .surname("Mitrofanova")
                .patronymic("Vladimirovna")
                .dateOfBirth(LocalDate.of(1999, 11, 23))
                .build());
        add(User.builder()
                .name("Masha")
                .surname("Ivanova")
                .patronymic("Dmitrievna")
                .dateOfBirth(LocalDate.of(1999, 10, 13))
                .build());
    }};

}
