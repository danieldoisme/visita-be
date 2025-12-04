package com.visita.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TourImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tour_id")
    private TourEntity tour;

    @Column(name = "image_url", nullable = false, length = 500)
    private String imageUrl;

    @Column(length = 255)
    private String description;
}

