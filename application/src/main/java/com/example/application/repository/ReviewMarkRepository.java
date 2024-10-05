package com.example.application.repository;

import com.example.application.repository.entity.ReviewMark;
import com.example.application.repository.entity.ReviewMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMarkRepository extends JpaRepository<ReviewMark, ReviewMarkId> {
}
