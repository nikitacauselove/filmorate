package com.example.application.repository.entity;

import com.example.api.dto.enums.EventOperation;
import com.example.api.dto.enums.EventType;
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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@Getter
@NoArgsConstructor
@Setter
@SequenceGenerator(allocationSize = 1, name = "events_id_seq")
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(generator = "events_id_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "created")
    private LocalDateTime timestamp;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventType eventType;

    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private EventOperation operation;

    @Column(name = "entity_id")
    private Long entityId;
}
