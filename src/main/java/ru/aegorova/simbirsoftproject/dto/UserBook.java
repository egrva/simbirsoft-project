package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserBook {
    @NotNull(message = "User cannot be null")
    @Valid
    @JsonDeserialize(as = User.class)
    private User user;
    @NotNull(message = "Book cannot be null")
    @Valid
    @JsonDeserialize(as = Book.class)
    private Book book;
    @NotNull(message = "dateTime cannot be null")
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX"
    )
    private ZonedDateTime dateTime;

    public static List<UserBook> userBooks = new ArrayList<>();

}
