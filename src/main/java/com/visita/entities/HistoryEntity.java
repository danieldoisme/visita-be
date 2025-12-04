package com.visita.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "history")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private TourEntity tour;

    @Column(name = "action_type", length = 100)
    private String actionType;   // VIEW, SEARCH, BOOK

    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}

