package com.example.filmorate.dao.impl;

import com.example.filmorate.dao.EventDao;
import com.example.filmorate.dao.mapper.EventRowMapper;
import com.example.filmorate.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EventDaoImpl extends DaoImpl implements EventDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Event event) {
        String sql = "insert into events (id, user_id, event_type, operation, entity_id) values (?, ?, ?, ?, ?)";
        event.setId(getNextId());

        jdbcTemplate.update(sql, event.getId(), event.getUserId(), event.getEventType().toString(), event.getOperation().toString(), event.getEntityId());
    }

    @Override
    public List<Event> findAllByUserId(int userId) {
        return jdbcTemplate.query("select * from events where user_id = ?", new EventRowMapper(), userId);
    }
}
