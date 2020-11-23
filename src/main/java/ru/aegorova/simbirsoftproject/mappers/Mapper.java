package ru.aegorova.simbirsoftproject.mappers;

import ru.aegorova.simbirsoftproject.dto.AbstractDto;
import ru.aegorova.simbirsoftproject.models.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDto> {

    E toEntity(D dto);

    D toDto(E entity);
}