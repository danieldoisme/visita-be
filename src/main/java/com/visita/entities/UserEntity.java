package com.visita.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(length = 15)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Male','Female','Other')")
    private Gender gender;

    private LocalDate dob;

    @Column(length = 255)
    private String address;

    @Column(name = "is_active")
    private Boolean isActive;  // BIT(1)

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Quan hệ
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BookingEntity> bookings;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ChatSessionEntity> chatSessions;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<HistoryEntity> histories;
}
