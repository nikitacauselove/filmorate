package com.example.backend.dao.impl;

import com.example.api.dto.enums.Genre;
import com.example.backend.dao.FilmDao;
import com.example.backend.dao.mapper.DirectorRowMapper;
import com.example.backend.dao.mapper.FilmRowMapper;
import com.example.backend.entity.Director;
import com.example.backend.entity.Film;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FilmDaoImpl extends DaoImpl implements FilmDao {

    private final JdbcTemplate jdbcTemplate;

    private void addGenres(Long filmId, List<Genre> genres) {
        for (Genre genre : genres) {
            Boolean exists = jdbcTemplate.queryForObject("select exists (select * from film_genres where film_id = ? and genre_id = ?)", Boolean.class, filmId, genre.getId());

            if (Boolean.FALSE.equals(exists)) {
                jdbcTemplate.update("insert into film_genres (film_id, genre_id) values (?, ?)", filmId, genre.getId());
            }
        }
    }

    private void updateGenres(Long filmId, List<Genre> genres) {
        deleteGenres(filmId);
        addGenres(filmId, genres);
    }

    private void deleteGenres(Long filmId) {
        jdbcTemplate.update("delete from film_genres where film_id = ?", filmId);
    }

    private void addDirectors(Long filmId, List<Director> directors) {
        for (Director director : directors) {
            Boolean exists = jdbcTemplate.queryForObject("select exists (select * from film_directors where film_id = ? and director_id = ?)", Boolean.class, filmId, director.getId());

            if (Boolean.FALSE.equals(exists)) {
                jdbcTemplate.update("insert into film_directors (film_id, director_id) values (?, ?)", filmId, director.getId());
            }
        }
    }

    private void updateDirectors(Long filmId, List<Director> directors) {
        deleteDirectorsByFilmId(filmId);
        addDirectors(filmId, directors);
    }

    private void deleteDirectorsByFilmId(Long filmId) {
        jdbcTemplate.update("delete from film_directors where film_id = ?", filmId);
    }

    @Override
    public void create(Film film) {
        String sql = "insert into films (id, name, description, release_date, duration, mpa_id) values (?, ?, ?, ?, ?, ?)";
        film.setId(getNextId());

        jdbcTemplate.update(sql, film.getId(), film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(), film.getMpa().getId());
        addGenres(film.getId(), film.getGenres());
        addDirectors(film.getId(), film.getDirectors());
    }

    @Override
    public void update(Film film) {
        String sql = "update films set name = ?, description = ?, release_date = ?, duration = ?, mpa_id = ? where id = ?";

        jdbcTemplate.update(sql, film.getName(), film.getDescription(), film.getReleaseDate(), film.getDuration(), film.getMpa().getId(), film.getId());
        updateGenres(film.getId(), film.getGenres());
        updateDirectors(film.getId(), film.getDirectors());
    }

    @Override
    public Film findById(Long id) {
        try {
            return jdbcTemplate.queryForObject("select * from films where id = ?", new FilmRowMapper(this), id);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Фильм с указанным идентификатором не найден.");
        }
    }

    @Override
    public boolean existsById(Long id) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("select exists (select * from films where id = ?)", Boolean.class, id));
    }

    @Override
    public List<Film> findAll() {
        return jdbcTemplate.query("select * from films order by id", new FilmRowMapper(this));
    }

    @Override
    public List<Film> findAllByDirectorId(Long directorId, Film.SortBy sortBy) {
        List<Long> listOfId = jdbcTemplate.queryForList("select film_id from film_directors where director_id = ?", Long.class, directorId);

        if (sortBy == Film.SortBy.LIKES) {
            String sql = "select * from films left join (select film_id, count(user_id) as count from film_likes group by film_id) as like_counts on films.id = like_counts.film_id where id in (%s) order by like_counts.count desc";

            return jdbcTemplate.query(String.format(sql, inSql(listOfId)), new FilmRowMapper(this));
        } else {
            return jdbcTemplate.query(String.format("select * from films where id in (%s) order by extract(year from release_date)", inSql(listOfId)), new FilmRowMapper(this));
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("delete from films where id = ?", id);
    }

    @Override
    public void addLike(Long id, Long userId) {
        jdbcTemplate.update("insert into film_likes (film_id, user_id) values (?, ?) on conflict do nothing", id, userId);
    }

    @Override
    public void deleteLike(Long id, Long userId) {
        jdbcTemplate.update("delete from film_likes where film_id = ? and user_id = ?", id, userId);
    }

    @Override
    public List<Film> findCommon(Long userId, Long friendId) {
        List<Long> listOfId = jdbcTemplate.queryForList("select film_id from film_likes where user_id = ? intersect select film_id from film_likes where user_id = ?", Long.class, userId, friendId);

        return jdbcTemplate.query(String.format("select * from films where id in (%s)", inSql(listOfId)), new FilmRowMapper(this));
    }

    @Override
    public List<Film> findPopular(Integer count, Optional<Long> genreId, Optional<Integer> year) {
        List<String> listOfCondition = new ArrayList<>();
        if (genreId.isPresent()) {
            List<Long> listOfId = jdbcTemplate.queryForList("select film_id from film_genres where genre_id = ?", Long.class, genreId.get());

            listOfCondition.add(String.format("films.id in (%s)", inSql(listOfId)));
        }
        if (year.isPresent()) {
            listOfCondition.add(String.format("extract (year from films.release_date) = %d", year.get()));
        }
        String conditions = listOfCondition.isEmpty() ? "" : String.format("where %s", String.join(" and ", listOfCondition));
        String sql = String.format("select * from films left join (select film_id, count(user_id) as count from film_likes group by film_id) as like_counts on films.id = like_counts.film_id %s order by like_counts.count desc nulls last, id limit ?", conditions);

        return jdbcTemplate.query(sql, new FilmRowMapper(this), count);
    }

    @Override
    public List<Film> findRecommendations(Long id) {
        String likedFilmsSql = String.format("select film_id from film_likes where user_id = %d", id);
        String commonFilmsSql = String.format("select user_id, count(film_id) as count from film_likes where film_id in (%s) and user_id <> %d group by user_id", likedFilmsSql, id);
        String mostRelevantUsersSql = String.format("select user_id, max(count) from (%s) group by user_id", commonFilmsSql);
        List<Long> listOfUserId = jdbcTemplate.queryForList(String.format("select user_id from (%s)", mostRelevantUsersSql), Long.class);
        List<Long> listOfFilmId = jdbcTemplate.queryForList(String.format("select film_id from film_likes where user_id in (%s) except %s", inSql(listOfUserId), likedFilmsSql), Long.class);

        return jdbcTemplate.query(String.format("select * from films where id in (%s)", inSql(listOfFilmId)), new FilmRowMapper(this));
    }

    @Override
    public List<Film> search(String query, List<String> by) {
        List<String> listOfCondition = new ArrayList<>();
        if (by.contains("director")) {
            List<Long> listOfDirectorId = jdbcTemplate.queryForList(String.format("select id from directors where name ilike '%%%s%%'", query), Long.class);

            if (!listOfDirectorId.isEmpty()) {
                List<Long> listOfId = jdbcTemplate.queryForList(String.format("select film_id from film_directors where director_id in (%s)", inSql(listOfDirectorId)), Long.class);

                listOfCondition.add(String.format("films.id in (%s)", inSql(listOfId)));
            }
        }
        if (by.contains("title")) {
            listOfCondition.add(String.format("films.name ilike '%%%s%%'", query));
        }
        String conditions = listOfCondition.isEmpty() ? "" : String.format("where %s", String.join(" or ", listOfCondition));
        String sql = String.format("select * from films left join (select film_id, count(user_id) as count from film_likes group by film_id) as like_counts on films.id = like_counts.film_id %s order by like_counts.count desc", conditions);

        return jdbcTemplate.query(sql, new FilmRowMapper(this));
    }

    @Override
    public List<Genre> findGenresByFilmId(Long filmId) {
        List<Long> listOfId = jdbcTemplate.queryForList("select genre_id from film_genres where film_id = ?", Long.class, filmId);

        return listOfId.stream()
                .map(Genre::findById)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> findLikingUsersByFilmId(Long filmId) {
        return jdbcTemplate.queryForList("select user_id from film_likes where film_id = ?", Long.class, filmId);
    }

    @Override
    public List<Director> findDirectorsByFilmId(Long filmId) {
        List<Long> listOfId = jdbcTemplate.queryForList("select director_id from film_directors where film_id = ?", Long.class, filmId);

        return jdbcTemplate.query(String.format("select * from directors where id in (%s)", inSql(listOfId)), new DirectorRowMapper());
    }
}
