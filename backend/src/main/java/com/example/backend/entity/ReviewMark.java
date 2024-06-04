package com.example.backend.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_marks")
public class ReviewMark {

    @EmbeddedId
    private ReviewMarkId id;
    @Enumerated(EnumType.STRING)
    private Review.MarkType markType;
}
