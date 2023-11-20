package com.example.filmorate.controller;

import com.example.filmorate.model.User;
import com.example.filmorate.model.dto.UserDto;
import com.example.filmorate.service.UserService;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@WebMvcTest(controllers = UserController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserControllerTest {
    @MockBean
    private final UserService userService;
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    public void create() throws Exception {
        UserDto userDto = new UserDto(null, "mail@mail.ru", "dolore", "Nick Name", LocalDate.parse("1946-08-20"), null);
        Mockito.when(userService.create(Mockito.any(User.class))).thenReturn(new User(1, "mail@mail.ru", "dolore", "Nick Name", LocalDate.parse("1946-08-20"), Collections.emptyList()));

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("mail@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("dolore"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Nick Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1946-08-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friends").isEmpty());
    }

    @Test
    public void createNullEmail() throws Exception {
        UserDto userDto = new UserDto(null, null, "dolore", "Nick Name", LocalDate.parse("1946-08-20"), null);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createNullLogin() throws Exception {
        UserDto userDto = new UserDto(null, "mail@mail.ru", null, "Nick Name", LocalDate.parse("1946-08-20"), null);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void createNullBirthday() throws Exception {
        UserDto userDto = new UserDto(null, "mail@mail.ru", "dolore", "Nick Name", null, null);

        mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void update() throws Exception {
        UserDto userDto = new UserDto(1, "mail@yandex.ru", "doloreUpdate", "est adipisicing", LocalDate.parse("1976-09-20"), null);
        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(new User(1, "mail@mail.ru", "dolore", "Nick Name", LocalDate.parse("1946-08-20"), Collections.emptyList()));
        Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(new User(1, "mail@yandex.ru", "doloreUpdate", "est adipisicing", LocalDate.parse("1976-09-20"), Collections.emptyList()));

        mockMvc.perform(MockMvcRequestBuilders.put("/users")
                        .content(objectMapper.writeValueAsString(userDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("mail@yandex.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("doloreUpdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("est adipisicing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1976-09-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friends").isEmpty());
    }

    @Test
    public void findById() throws Exception {
        Mockito.when(userService.findById(Mockito.anyInt())).thenReturn(new User(1, "mail@yandex.ru", "doloreUpdate", "est adipisicing", LocalDate.parse("1976-09-20"), Collections.emptyList()));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("mail@yandex.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.login").value("doloreUpdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("est adipisicing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1976-09-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.friends").isEmpty());
    }

    @Test
    public void findAll() throws Exception {
        Mockito.when(userService.findAll()).thenReturn(List.of(new User(1, "mail@yandex.ru", "doloreUpdate", "est adipisicing", LocalDate.parse("1976-09-20"), Collections.emptyList())));

        mockMvc.perform(MockMvcRequestBuilders.get("/users"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email").value("mail@yandex.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].login").value("doloreUpdate"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("est adipisicing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").value("1976-09-20"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].friends").isEmpty());
    }
}
