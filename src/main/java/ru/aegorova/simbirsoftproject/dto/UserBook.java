package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserBook {
    @JsonDeserialize(as = User.class)
    private User user;
    @JsonDeserialize(as = Book.class)
    private Book book;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX"
    )
    private ZonedDateTime dateTime;

    public static List<UserBook> userBooks = new ArrayList<>();

}
