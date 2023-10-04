package com.filmorate.filmorate.dao.mapper;

import com.filmorate.filmorate.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Review(rs.getInt("id"), rs.getString("content"), rs.getBoolean("is_positive"), rs.getInt("user_id"),  rs.getInt("film_id"), rs.getInt("useful"));
    }
}