package com.visita.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.visita.entities.BookingEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, String> {

        @org.springframework.data.jpa.repository.Query("SELECT b FROM BookingEntity b WHERE " +
                        "LOWER(b.user.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(b.tour.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
                        "LOWER(b.user.username) LIKE LOWER(CONCAT('%', :keyword, '%'))")
        org.springframework.data.domain.Page<BookingEntity> searchBookings(String keyword,
                        org.springframework.data.domain.Pageable pageable);

        org.springframework.data.domain.Page<BookingEntity> findByUserAndStatusNot(com.visita.entities.UserEntity user,
                        com.visita.entities.BookingStatus status, org.springframework.data.domain.Pageable pageable);

        org.springframework.data.domain.Page<BookingEntity> findByUserAndStatus(com.visita.entities.UserEntity user,
                        com.visita.entities.BookingStatus status, org.springframework.data.domain.Pageable pageable);

        boolean existsByUser_UsernameAndTour_TourIdAndStatus(String username, String tourId,
                        com.visita.entities.BookingStatus status);

        long countByBookingDateBetween(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);

        org.springframework.data.domain.Page<BookingEntity> findByStaff_UserId(String staffId,
                        org.springframework.data.domain.Pageable pageable);
}
