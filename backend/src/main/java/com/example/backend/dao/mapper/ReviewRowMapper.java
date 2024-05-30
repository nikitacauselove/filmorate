package com.example.backend.dao.mapper;

import com.example.backend.entity.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Review(rs.getLong("id"), rs.getString("content"), rs.getBoolean("is_positive"), rs.getLong("user_id"),  rs.getLong("film_id"), rs.getInt("useful"));
    }
}
