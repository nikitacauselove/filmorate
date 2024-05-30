package com.example.backend.dao.mapper;

import com.example.api.dto.enums.Genre;
import com.example.api.dto.enums.Mpa;
import com.example.backend.dao.FilmDao;
import com.example.backend.entity.Director;
import com.example.backend.entity.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class FilmRowMapper implements RowMapper<Film> {

    private final FilmDao filmDao;

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Genre> genreList = filmDao.findGenresByFilmId(rs.getLong("id"));
        List<Long> likingUsers = filmDao.findLikingUsersByFilmId(rs.getLong("id"));
        List<Director> directorList = filmDao.findDirectorsByFilmId(rs.getLong("id"));

        return new Film(rs.getLong("id"), rs.getString("name"), rs.getString("description"), rs.getDate("release_date").toLocalDate(), rs.getInt("duration"), Mpa.findById(rs.getLong("mpa_id")), genreList, likingUsers, directorList);
    }
}
