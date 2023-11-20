package com.example.filmorate.controller;

import com.example.filmorate.model.Director;
import com.example.filmorate.model.dto.DirectorDto;
import com.example.filmorate.service.DirectorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@WebMvcTest(controllers = DirectorController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DirectorControllerTest {
    @MockBean
    private final DirectorService directorService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    public void create() throws Exception {
        DirectorDto directorDto = new DirectorDto(10, "Director");
        Mockito.when(directorService.create(Mockito.any(Director.class))).thenReturn(new Director(1, "Director"));

        mockMvc.perform(MockMvcRequestBuilders.post("/directors")
                        .content(objectMapper.writeValueAsString(directorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Director"));
    }

    @Test
    public void createNullName() throws Exception {
        DirectorDto directorDto = new DirectorDto(10, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/directors")
                        .content(objectMapper.writeValueAsString(directorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void update() throws Exception {
        DirectorDto directorDto = new DirectorDto(1, "Director updated");
        Mockito.when(directorService.findById(Mockito.anyInt())).thenReturn(new Director(1, "Director"));
        Mockito.when(directorService.update(Mockito.any(Director.class))).thenReturn(new Director(1, "Director updated"));

        mockMvc.perform(MockMvcRequestBuilders.put("/directors")
                        .content(objectMapper.writeValueAsString(directorDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Director updated"));
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(directorService.findById(Mockito.anyInt())).thenReturn(new Director(1, "Director"));

        mockMvc.perform(MockMvcRequestBuilders.get("/directors/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Director"));
    }

    @Test
    public void findAll() throws Exception {
        Mockito.when(directorService.findAll()).thenReturn(List.of(new Director(1, "Director updated")));

        mockMvc.perform(MockMvcRequestBuilders.get("/directors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Director updated"));
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/directors/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
