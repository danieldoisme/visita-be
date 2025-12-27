package com.visita.controller;

import com.visita.dto.response.ApiResponse;
import com.visita.entities.TourEntity;
import com.visita.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping("/{tourId}")
    public ApiResponse<String> addFavorite(@PathVariable String tourId) {
        favoriteService.addFavorite(tourId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Tour added to favorites successfully");
        return response;
    }

    @DeleteMapping("/{tourId}")
    public ApiResponse<String> removeFavorite(@PathVariable String tourId) {
        favoriteService.removeFavorite(tourId);
        ApiResponse<String> response = new ApiResponse<>();
        response.setResult("Tour removed from favorites successfully");
        return response;
    }

    @GetMapping
    public ApiResponse<List<TourEntity>> getMyFavorites() {
        ApiResponse<List<TourEntity>> response = new ApiResponse<>();
        response.setResult(favoriteService.getMyFavorites());
        return response;
    }
}
