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
public abstract class HumanAbstractDto extends AbstractDto {

    @JsonView(Views.Public.class)
    @NotNull(message = "First name cannot be null")
    private String firstName;

    @JsonView(Views.Public.class)
    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @JsonView(Views.Internal.class)
    private String middleName;

}
