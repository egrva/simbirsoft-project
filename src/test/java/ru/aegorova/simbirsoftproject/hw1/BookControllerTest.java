package ru.aegorova.simbirsoftproject.hw1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllBooksTest() throws Exception {
        mockMvc.perform(get("/api/books/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].author"
                        , hasItems("Fyodor Dostoyevski", "Ivan Turgenev", "Leo Tolstoy")));
    }

    @Test
    public void getBookByTitleTest() throws Exception {
        mockMvc.perform(get("/api/books/getByAuthor/Fyodor Dostoyevski"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].author"
                        , hasItem("Fyodor Dostoyevski")));
    }

    @Test
    public void addBookTest() throws Exception {
        String bookInJson = "{\"title\":\"And Quiet Flows the Don\"," +
                "\"author\":\"Mikhail Aleksandrovich Sholokhov\"," +
                "\"genre\":\"Novel\"}";
        mockMvc.perform(post("/api/books/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookInJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].author"
                        , hasItem("Mikhail Aleksandrovich Sholokhov")));
    }

    @Test
    public void deleteBookTest() throws Exception {
        mockMvc.perform(delete("/api/books/delete")
                .param("author", "Fyodor Dostoyevski")
                .param("title", "Crime and Punishment"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/books/all"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].author"
                        , hasItems("Ivan Turgenev", "Leo Tolstoy")));
    }


}
