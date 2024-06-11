package com.example.backend.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Table(name = "events")
@SequenceGenerator(name = "events_id_seq", allocationSize = 1)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "events_id_seq")
    private Long id;
    @Column(name = "created", columnDefinition = "TIMESTAMP")
    private LocalDateTime timestamp;
    private Long userId;
    @Enumerated(EnumType.STRING)
    private EventType eventType;
    @Enumerated(EnumType.STRING)
    private Operation operation;
    private Long entityId;

    public enum EventType {
        FRIEND,
        LIKE,
        REVIEW
    }

    public enum Operation {
        ADD,
        REMOVE,
        UPDATE
    }
}
