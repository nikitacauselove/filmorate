package com.example.filmorate.controller;

import com.example.filmorate.entity.Review;
import com.example.filmorate.dto.ReviewDto;
import com.example.filmorate.mapper.ReviewMapper;
import com.example.filmorate.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
