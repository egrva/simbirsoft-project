package ru.aegorova.simbirsoftproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aegorova.simbirsoftproject.dto.GenreDto;
import ru.aegorova.simbirsoftproject.mappers.GenreMapper;
import ru.aegorova.simbirsoftproject.repositories.GenreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;

    @Override
    public List<GenreDto> getAllGenres() {
        return genreRepository.findAll().stream().map(
                genre -> genreMapper.toDto(genre)
        ).collect(Collectors.toList());
    }

    @Override
    public GenreDto addGengre(GenreDto genreDto) {
        return genreMapper.toDto(genreRepository.save(
                genreMapper.toEntity(genreDto)
        ));
    }

    @Override
    public Map<String, Integer> getStatistic(GenreDto genreDto) {
        Map<String, Integer> map = new HashMap<>();
        map.put(genreDto.getGenreName(), genreRepository.countStatistic(genreDto.getGenreName()));
        return map;
    }

    @Override
    public void deleteGenreById(Long genreId) {
        genreRepository.deleteById(genreId);
    }
}
