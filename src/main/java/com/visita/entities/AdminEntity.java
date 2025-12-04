package com.visita.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_id")
    private Long adminId;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;   // đã mã hoá (BCrypt)

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 1 Admin có thể xử lý nhiều phiên chat
    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    private List<ChatSessionEntity> chatSessions;
}
