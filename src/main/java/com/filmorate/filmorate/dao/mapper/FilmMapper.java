package com.filmorate.filmorate.dao.mapper;

import com.filmorate.filmorate.dao.FilmDao;
import com.filmorate.filmorate.model.Director;
import com.filmorate.filmorate.model.Film;
import com.filmorate.filmorate.model.Genre;
import com.filmorate.filmorate.model.Mpa;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
public class FilmMapper implements RowMapper<Film> {
    private final FilmDao filmDao;

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<Genre> genres = filmDao.findGenresByFilmId(rs.getInt("id"));
        List<Integer> likingUsers = filmDao.findLikingUsersByFilmId(rs.getInt("id"));
        List<Director> directors = filmDao.findDirectorsByFilmId(rs.getInt("id"));

        return new Film(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDate("release_date").toLocalDate(), rs.getInt("duration"), Mpa.findById(rs.getInt("mpa_id")), genres, likingUsers, directors);
    }
}
