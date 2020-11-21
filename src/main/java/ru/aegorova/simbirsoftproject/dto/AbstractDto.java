package ru.aegorova.simbirsoftproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
public abstract class AbstractDto implements Serializable {

    @JsonView(Views.Public.class)
    private Long id;

    @JsonView(Views.Internal.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    private ZonedDateTime created;

    @JsonView(Views.Internal.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSXXX")
    private ZonedDateTime updated;

}