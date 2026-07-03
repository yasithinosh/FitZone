package com.fitzone.api.controller;

import com.fitzone.api.dto.request.TrainerRequest;
import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.dto.response.TrainerResponse;
import com.fitzone.api.service.TrainerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TrainerResponse>>> getAllTrainers() {
        return ResponseEntity.ok(ApiResponse.success(trainerService.getAllTrainers(), "Trainers retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TrainerResponse>> getTrainerById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(trainerService.getTrainerById(id), "Trainer retrieved successfully"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TrainerResponse>> createTrainer(@Valid @RequestBody TrainerRequest request) {
        return ResponseEntity.ok(ApiResponse.success(trainerService.createTrainer(request), "Trainer created successfully"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<TrainerResponse>> updateTrainer(@PathVariable Long id, @Valid @RequestBody TrainerRequest request) {
        return ResponseEntity.ok(ApiResponse.success(trainerService.updateTrainer(id, request), "Trainer updated successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteTrainer(@PathVariable Long id) {
        trainerService.deleteTrainer(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Trainer deleted successfully"));
    }
}
