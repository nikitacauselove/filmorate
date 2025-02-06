package com.example.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Embeddable
@EqualsAndHashCode
@FieldNameConstants
@Getter
@NoArgsConstructor
@Setter
public class ReviewMarkId implements Serializable {

    @Column(name = "review_id")
    private Long reviewId;

    @Column(name = "user_id")
    private Long userId;
}
