package com.filmorate.filmorate.controller;

import com.filmorate.filmorate.model.Review;
import com.filmorate.filmorate.model.dto.ReviewDto;
import com.filmorate.filmorate.model.mapper.ReviewMapper;
import com.filmorate.filmorate.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "reviews")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ReviewDto create(@RequestBody @Valid ReviewDto reviewDto) {
        Review review = ReviewMapper.toReview(reviewDto);

        return ReviewMapper.toReviewDto(reviewService.create(review));
    }

    @PutMapping
    public ReviewDto update(@RequestBody @Valid ReviewDto reviewDto) {
        Review review = ReviewMapper.toReview(reviewDto, reviewService.findById(reviewDto.getReviewId()));

        return ReviewMapper.toReviewDto(reviewService.update(review));
    }

    @GetMapping("/{id}")
    public ReviewDto findById(@PathVariable int id) {
        return ReviewMapper.toReviewDto(reviewService.findById(id));
    }

    @GetMapping
    public List<ReviewDto> findAll(@RequestParam Optional<Integer> filmId, @RequestParam(defaultValue = "10") int count) {
        return ReviewMapper.toReviewDto(reviewService.findAll(filmId, count));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        reviewService.deleteById(id);
    }

    @PutMapping("{id}/like/{userId}")
    public void addLike(@PathVariable int id, @PathVariable int userId) {
        reviewService.addMark(id, userId, Review.MarkType.LIKE);
    }

    @DeleteMapping("{id}/like/{userId}")
    public void deleteLike(@PathVariable int id, @PathVariable int userId) {
        reviewService.deleteMark(id, userId, Review.MarkType.LIKE);
    }

    @PutMapping("{id}/dislike/{userId}")
    public void addDislike(@PathVariable int id, @PathVariable int userId) {
        reviewService.addMark(id, userId, Review.MarkType.DISLIKE);
    }

    @DeleteMapping("{id}/dislike/{userId}")
    public void deleteDislike(@PathVariable int id, @PathVariable int userId) {
        reviewService.deleteMark(id, userId, Review.MarkType.DISLIKE);
    }
}
