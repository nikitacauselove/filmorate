package com.example.backend.dao.mapper;

import com.example.backend.entity.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

//public class EventRowMapper implements RowMapper<Event> {
//
//    @Override
//    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Event.EventType eventType = Event.EventType.valueOf(rs.getString("event_type"));
//        Event.Operation operation = Event.Operation.valueOf(rs.getString("operation"));
//
//        return new Event(rs.getLong("id"), rs.getTimestamp("created").getTime(), rs.getLong("user_id"), eventType, operation, rs.getLong("entity_id"));
//    }
//}
