package com.example.backend.dao.impl;

import com.example.backend.dao.EventDao;
import com.example.backend.dao.mapper.EventRowMapper;
import com.example.backend.entity.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventDaoImpl extends DaoImpl implements EventDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(Event event) {
        String sql = "insert into events (id, user_id, event_type, operation, entity_id) values (?, ?, ?, ?, ?)";
        event.setId(getNextId());

        jdbcTemplate.update(sql, event.getId(), event.getUserId(), event.getEventType().toString(), event.getOperation().toString(), event.getEntityId());
    }

    @Override
    public List<Event> findAllByUserId(Long userId) {
        return jdbcTemplate.query("select * from events where user_id = ?", new EventRowMapper(), userId);
    }
}
