package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.aegorova.simbirsoftproject.utils.Views;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonView(Views.Public.class)
    @NotNull(message = "User's name cannot be null")
    private String name;

    @JsonView(Views.Public.class)
    @NotNull(message = "User's surname cannot be null")
    private String surname;

    @JsonView(Views.Public.class)
    @NotNull(message = "User's patronymic cannot be null")
    private String patronymic;

    @JsonView(Views.Internal.class)
    @NotNull(message = "User's date of birth cannot be null")
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
