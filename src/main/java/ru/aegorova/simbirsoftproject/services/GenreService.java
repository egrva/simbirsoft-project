package ru.aegorova.simbirsoftproject.services;

import ru.aegorova.simbirsoftproject.dto.GenreDto;

import java.util.List;
import java.util.Map;

public interface GenreService {
    //Просмотр всех жанров.
    public List<GenreDto> getAllGenres();

    //Добавление нового жанра.
    public GenreDto addGengre(GenreDto genreDto);

    //Вывод статистики Жанр - количество книг
    public Map<String, Integer> getStatistic(GenreDto genreDto);

    //удалить жанр по id
    void deleteGenreById(Long genreId);

}
