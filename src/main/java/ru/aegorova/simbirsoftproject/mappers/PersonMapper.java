package ru.aegorova.simbirsoftproject.mappers;

import org.springframework.stereotype.Component;
import ru.aegorova.simbirsoftproject.dto.PersonDto;
import ru.aegorova.simbirsoftproject.models.Person;

@Component
public class PersonMapper extends AbstractMapper<Person, PersonDto> {

    PersonMapper() {
        super(Person.class, PersonDto.class);
    }
}
