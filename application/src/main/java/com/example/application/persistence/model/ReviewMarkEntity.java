package com.example.application.persistence.model;

import com.example.application.domain.MarkType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
@Setter
@Table(name = "review_mark")
public class ReviewMarkEntity {

    @EmbeddedId
    private ReviewMarkEntityId id;

    @Column(name = "mark_type")
    @Enumerated(EnumType.STRING)
    private MarkType markType;
}
