package com.example.filmorate.dao.mapper;

import com.example.filmorate.model.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event.EventType eventType = Event.EventType.valueOf(rs.getString("event_type"));
        Event.Operation operation = Event.Operation.valueOf(rs.getString("operation"));

        return new Event(rs.getInt("id"), rs.getTimestamp("created").getTime(), rs.getInt("user_id"), eventType, operation, rs.getInt("entity_id"));
    }
}
