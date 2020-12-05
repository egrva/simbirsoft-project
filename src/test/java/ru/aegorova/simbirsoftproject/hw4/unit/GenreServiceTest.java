package ru.aegorova.simbirsoftproject.hw4.unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.aegorova.simbirsoftproject.dto.GenreDto;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.repositories.GenreRepository;
import ru.aegorova.simbirsoftproject.services.GenreService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenreServiceTest {

    @Autowired
    private GenreService genreService;

    @MockBean
    private GenreRepository genreRepository;

    @Before
    public void init() {
        Genre genre = new Genre();
        genre.setGenreName("genreName");
        List<Genre> genres = Collections.singletonList(genre);
        Mockito.when(genreRepository.findAll()).thenReturn(genres);
        Mockito.when(genreRepository.save(Mockito.any(Genre.class))).then(returnsFirstArg());
    }

    @Test
    public void addGenreTest() {
        GenreDto genreDto = new GenreDto() {{
            setGenreName("name");
        }};
        assertThat(genreService.addGengre(genreDto).getGenreName().equals(genreDto.getGenreName()));
    }

    @Test
    public void getAllGenresTest() {
        assertThat((genreService.getAllGenres()).size() == 1);
        assertThat((genreService.getAllGenres()).stream().findFirst()
                .get().getGenreName().equals("genreName"));
    }
}
