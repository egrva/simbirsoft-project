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
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsersTest() throws Exception {
        mockMvc.perform(get("/api/users/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name"
                        , hasItems("Nastya", "Katya", "Masha")));
    }

    @Test
    public void getUserByNameTest() throws Exception {
        mockMvc.perform(get("/api/users/getByName/Nastya"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].name"
                        , hasItem("Nastya")));
    }

    @Test
    public void addUserTest() throws Exception {
        String userInJson = "{\"name\":\"Sveta\"," +
                "\"surname\":\"Mikhailova\"," +
                "\"patronymic\":\"Danilovna\"," +
                "\"dateOfBirth\":\"2007-12-03\"}";
        mockMvc.perform(post("/api/users/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userInJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].name"
                        , hasItem("Sveta")));
    }

    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/users/delete")
                .param("name", "Nastya")
                .param("surname", "Egorova")
                .param("patronymic", "Andreevna"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/users/all"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].name"
                        , hasItems("Katya", "Masha")));
    }


}
