package com.example.backend.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class ReviewMarkId implements Serializable {

    private Long reviewId;
    private Long userId;
}
