package ru.aegorova.simbirsoftproject.hw4.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getBooksByGenreAndPublicationDateTest() throws Exception {
        mockMvc.perform(get("/api/books/findByGenreAndPublicationDate?genreId=2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name", hasItems("Война и мир", "Анна Каренина", "Пиковая Дама")));
    }

}
