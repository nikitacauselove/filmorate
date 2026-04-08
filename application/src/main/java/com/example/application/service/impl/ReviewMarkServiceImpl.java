package com.example.application.service.impl;

import com.example.application.entity.ReviewMark;
import com.example.application.entity.ReviewMarkId;
import com.example.application.repository.ReviewMarkRepository;
import com.example.application.service.ReviewMarkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewMarkServiceImpl implements ReviewMarkService {

    private final ReviewMarkRepository reviewMarkRepository;

    @Override
    public ReviewMark create(ReviewMark reviewMark) {
        return reviewMarkRepository.save(reviewMark);
    }

    @Override
    public void deleteById(ReviewMarkId id) {
        reviewMarkRepository.deleteById(id);
    }
}
