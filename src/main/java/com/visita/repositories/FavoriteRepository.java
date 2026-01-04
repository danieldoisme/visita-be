package com.visita.repositories;

import com.visita.entities.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, String> {

    List<FavoriteEntity> findByUser_UserId(String userId);

    boolean existsByUser_UserIdAndTour_TourId(String userId, String tourId);

    Optional<FavoriteEntity> findByUser_UserIdAndTour_TourId(String userId, String tourId);

    void deleteByUser_UserIdAndTour_TourId(String userId, String tourId);
}
