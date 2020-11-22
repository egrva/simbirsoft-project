package ru.aegorova.simbirsoftproject.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aegorova.simbirsoftproject.dto.GenreDto;
import ru.aegorova.simbirsoftproject.services.GenreService;
import ru.aegorova.simbirsoftproject.utils.Views;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    //Просмотр всех жанров.
    @GetMapping("/getAll")
    @JsonView(Views.Public.class)
    public List<GenreDto> getAllGenres() {
        return genreService.getAllGenres();
    }

    //Добавление нового жанра.
    @PostMapping("/add")
    @JsonView(Views.Public.class)
    public GenreDto addGenre(@RequestBody GenreDto genreDto) {
        return genreService.addGengre(genreDto);
    }

    //Вывод статистики Жанр - количество книг
    @PostMapping("/statistic")
    @JsonView(Views.Public.class)
    public Map<String, Integer> getStatistic(@RequestBody GenreDto genreDto) {
        return genreService.getStatistic(genreDto);
    }

    //Удалить жанр
    @DeleteMapping("/delete/{genreId}")
    @JsonView(Views.Public.class)
    public ResponseEntity<?> deleteGenreById(@PathVariable Long genreId) {
        genreService.deleteGenreById(genreId);
        return ResponseEntity.ok().build();
    }

}
