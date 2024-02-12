package com.example.filmorate.controller;

import com.example.filmorate.entity.Film;
import com.example.filmorate.entity.Genre;
import com.example.filmorate.entity.Mpa;
import com.example.filmorate.entity.User;
import com.example.filmorate.dto.UserDto;
import com.example.filmorate.service.impl.UserService;
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1946-08-20"));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1976-09-20"));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value("1976-09-20"));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").value("1976-09-20"));
    }

    @Test
    public void addFriend() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/users/1/friends/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void deleteFriend() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1/friends/2"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findAllFriend() throws Exception {
        Mockito.when(userService.findAllFriends(Mockito.anyInt())).thenReturn(List.of(new User(2, "friend@mail.ru", "friend", "friend adipisicing", LocalDate.parse("1976-08-20"), Collections.emptyList())));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/friends"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email").value("friend@mail.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].login").value("friend"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("friend adipisicing"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").value("1976-08-20"));
    }

    @Test
    public void findCommonFriends() throws Exception {
        Mockito.when(userService.findCommonFriends(Mockito.anyInt(), Mockito.anyInt())).thenReturn(List.of(new User(3, "friend@common.ru", "common", "common", LocalDate.parse("2000-08-20"), Collections.emptyList())));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/friends/common/2"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].email").value("friend@common.ru"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].login").value("common"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("common"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").value("2000-08-20"));
    }

    @Test
    public void findRecommendations() throws Exception {
        Film film = new Film(2, "New film", "New film about friends", LocalDate.parse("1999-04-30"), 120, Mpa.PG13, List.of(Genre.COMEDY, Genre.DRAMA), List.of(2), Collections.emptyList());
        Mockito.when(userService.findRecommendations(Mockito.anyInt())).thenReturn(List.of(film));

        mockMvc.perform(MockMvcRequestBuilders.get("/users/1/recommendations"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("New film"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].description").value("New film about friends"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].releaseDate").value("1999-04-30"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].duration").value(120))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].mpa.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].mpa.name").value("PG-13"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genres.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genres.[0].name").value("Комедия"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genres.[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].genres.[1].name").value("Драма"));
    }
}
