package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.aegorova.simbirsoftproject.utils.Views;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibraryCardDto extends AbstractDto {

    @NotNull(message = "Person cannot be null")
    @Valid
    @JsonView(Views.Internal.class)
    private Long personId;

    @NotNull(message = "Book cannot be null")
    @Valid
    @JsonView(Views.Internal.class)
    private Long bookId;

    @NotNull(message = "Turning date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    @JsonView(Views.Internal.class)
    private ZonedDateTime dateTime;

}
