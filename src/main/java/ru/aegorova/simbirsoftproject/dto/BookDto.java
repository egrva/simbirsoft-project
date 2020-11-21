package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.aegorova.simbirsoftproject.utils.Views;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends AbstractDto {

    @JsonView(Views.Public.class)
    @NotNull(message = "Book's title cannot be null")
    private String name;

    @JsonView(Views.Public.class)
    @Valid
    @NotNull(message = "Book's author cannot be null")
    private Long authorId;

    @Valid
    @JsonView(Views.Internal.class)
    private Set<Long> genreIds;

    @Valid
    @JsonView(Views.Internal.class)
    private Set<LibraryCardDto> libraryCards;

}
