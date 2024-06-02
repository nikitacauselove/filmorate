package com.example.backend.dao.impl;

//import com.example.backend.dao.DirectorDao;
//import com.example.backend.dao.mapper.DirectorRowMapper;
//import com.example.backend.entity.Director;
//import lombok.RequiredArgsConstructor;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//@Component
//@RequiredArgsConstructor
//public class DirectorDaoImpl extends DaoImpl implements DirectorDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void create(Director director) {
//        director.setId(getNextId());
//
//        jdbcTemplate.update("insert into directors (id, name) values (?, ?)", director.getId(), director.getName());
//    }
//
//    @Override
//    public void update(Director director) {
//        jdbcTemplate.update("update directors set name = ? where id = ?", director.getName(), director.getId());
//    }
//
//    @Override
//    public Director findById(Long id) {
//        try {
//            return jdbcTemplate.queryForObject("select * from directors where id = ?", new DirectorRowMapper(), id);
//        } catch (EmptyResultDataAccessException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Режиссёр с указанным идентификатором не найден.");
//        }
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        return Boolean.TRUE.equals(jdbcTemplate.queryForObject("select exists (select * from directors where id = ?)", Boolean.class, id));
//    }
//
//    @Override
//    public List<Director> findAll() {
//        return jdbcTemplate.query("select * from directors", new DirectorRowMapper());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        jdbcTemplate.update("delete from directors where id = ?", id);
//    }
//}
