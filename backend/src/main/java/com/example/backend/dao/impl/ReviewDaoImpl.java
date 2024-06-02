package com.example.backend.dao.impl;

//import com.example.backend.dao.ReviewDao;
//import com.example.backend.dao.mapper.ReviewRowMapper;
//import com.example.backend.entity.Review;
//import lombok.RequiredArgsConstructor;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//import java.util.Optional;

//@Component
//@RequiredArgsConstructor
//public class ReviewDaoImpl extends DaoImpl implements ReviewDao {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Override
//    public void create(Review review) {
//        String sql = "insert into reviews (id, content, is_positive, user_id, film_id, useful) values (?, ?, ?, ?, ?, ?)";
//        review.setId(getNextId());
//
//        jdbcTemplate.update(sql, review.getId(), review.getContent(), review.getIsPositive(), review.getUserId(), review.getFilmId(), review.getUseful());
//    }
//
//    @Override
//    public void update(Review review) {
//        String sql = "update reviews set content = ?, is_positive = ?, user_id = ?, film_id = ?, useful = ? where id = ?";
//
//        jdbcTemplate.update(sql, review.getContent(), review.getIsPositive(), review.getUserId(), review.getFilmId(), review.getUseful(), review.getId());
//    }
//
//    @Override
//    public Review findById(Long id) {
//        try {
//            return jdbcTemplate.queryForObject("select * from reviews where id = ?", new ReviewRowMapper(), id);
//        } catch (EmptyResultDataAccessException exception) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Отзыв с указанным идентификатором не найден.");
//        }
//    }
//
//    @Override
//    public List<Review> findAll(Optional<Long> filmId, Integer count) {
//        String filmCondition = filmId.isPresent() ? String.format("where film_id = %s", filmId.get()) : "";
//
//        return jdbcTemplate.query(String.format("select * from reviews %s order by useful desc limit %s", filmCondition, count), new ReviewRowMapper());
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        jdbcTemplate.update("delete from reviews where id = ?", id);
//    }
//
//    @Override
//    public void addMark(Long id, Long userId, Review.MarkType markType) {
//        Integer oldUseful = jdbcTemplate.queryForObject("select useful from reviews where id = ?", Integer.class, id);
//        Integer useful = markType == Review.MarkType.LIKE ? ++oldUseful : --oldUseful;
//
//        jdbcTemplate.update("insert into review_marks (review_id, user_id, mark_type) values (?, ?, ?)", id, userId, markType.toString());
//        jdbcTemplate.update("update reviews set useful = ? where id = ?", useful, id);
//    }
//
//    @Override
//    public void deleteMark(Long id, Long userId, Review.MarkType markType) {
//        Integer oldUseful = jdbcTemplate.queryForObject("select useful from reviews where id = ?", Integer.class, id);
//        Integer useful = markType == Review.MarkType.DISLIKE ? ++oldUseful : --oldUseful;
//
//        jdbcTemplate.update("delete into review_marks (review_id, user_id, mark_type) values (?, ?, ?)", id, userId, markType.toString());
//        jdbcTemplate.update("update reviews set useful = ? where id = ?", useful, id);
//    }
//}
