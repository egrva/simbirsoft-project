package ru.aegorova.simbirsoftproject.hw4.unit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.aegorova.simbirsoftproject.dto.GenreDto;
import ru.aegorova.simbirsoftproject.mappers.GenreMapper;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.GenreRepository;
import ru.aegorova.simbirsoftproject.services.GenreService;
import ru.aegorova.simbirsoftproject.services.GenreServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.mock;

public class GenreServiceTest {

    private GenreService genreService;
    private GenreRepository genreRepository;
    private GenreMapper genreMapper;



    @Before
    public void init() {
        genreMapper = mock(GenreMapper.class);
        genreRepository = mock(GenreRepository.class);
        genreService = new GenreServiceImpl(genreRepository,genreMapper);

        Genre genre = new Genre();
        genre.setGenreName("genreName");
        genre.setId(1L);

        GenreDto genreDto = new GenreDto();
        genreDto.setGenreName("name");
        genreDto.setId(1L);

        List<Genre> genres = Collections.singletonList(genre);
        Mockito.when(genreRepository.findAll()).thenReturn(genres);
        Mockito.when(genreRepository.save(Mockito.any(Genre.class))).then(returnsFirstArg());
        Mockito.when(genreRepository.countStatistic(Mockito.anyString())).thenReturn(1);
        Mockito.when(genreMapper.toDto(Mockito.any(Genre.class))).thenReturn(genreDto);
        Mockito.when(genreMapper.toEntity(Mockito.any(GenreDto.class))).thenReturn(genre);
    }

    @Test
    public void addGenreTest() {
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreName("name");
        genreDto.setId(1L);
        assertThat(genreService.addGengre(genreDto).getGenreName().equals(genreDto.getGenreName()));
    }

    @Test
    public void getAllGenresTest() {
        assertThat((genreService.getAllGenres()).size() == 1);
        assertThat((genreService.getAllGenres()).stream().findFirst()
                .get().getGenreName().equals("genreName"));
    }

    @Test
    public void getStatisticTest() {
        GenreDto genreDto = new GenreDto();
        genreDto.setGenreName("name");
        genreDto.setId(1L);
        assertThat(genreService.getStatistic(genreDto).get("name") == 1);
    }


}
