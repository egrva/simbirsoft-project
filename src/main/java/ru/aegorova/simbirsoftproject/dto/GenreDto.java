package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.aegorova.simbirsoftproject.utils.Views;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto extends AbstractDto {

    @JsonView(Views.Public.class)
    @NotNull(message = "Genre cannot be null")
    private String genreName;

}
