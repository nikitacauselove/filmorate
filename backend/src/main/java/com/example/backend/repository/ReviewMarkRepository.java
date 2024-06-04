package com.example.backend.repository;

import com.example.backend.entity.ReviewMark;
import com.example.backend.entity.ReviewMarkId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewMarkRepository extends JpaRepository<ReviewMark, ReviewMarkId> {
}
