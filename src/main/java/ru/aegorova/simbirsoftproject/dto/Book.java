package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.aegorova.simbirsoftproject.utils.Views;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @JsonView(Views.Public.class)
    @NotNull(message = "Book's title cannot be null")
    private String title;

    @JsonView(Views.Public.class)
    @NotNull(message = "Book's author cannot be null")
    private String author;

    @JsonView(Views.Internal.class)
    @NotNull(message = "Book's genre cannot be null")
    private String genre;


}
