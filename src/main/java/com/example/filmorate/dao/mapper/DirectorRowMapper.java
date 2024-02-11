package com.example.filmorate.dao.mapper;

import com.example.filmorate.entity.Director;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DirectorRowMapper implements RowMapper<Director> {
    @Override
    public Director mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Director(rs.getInt("id"), rs.getString("name"));
    }
}
