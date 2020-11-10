package ru.aegorova.simbirsoftproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate dateOfBirth;

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
