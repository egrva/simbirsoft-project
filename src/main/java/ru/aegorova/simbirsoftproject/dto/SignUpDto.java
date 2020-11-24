package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto extends HumanAbstractDto{

    @NotNull(message = "Login cannot be null")
    private String login;

    @NotNull(message = "Password cannot be null")
    private String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @NotNull(message = "Role cannot be null")
    private String role;
}
