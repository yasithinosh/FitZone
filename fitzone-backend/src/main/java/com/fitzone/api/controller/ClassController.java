package com.fitzone.api.controller;

import com.fitzone.api.dto.request.ClassRequest;
import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.dto.response.ClassResponse;
import com.fitzone.api.service.ClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService classService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClassResponse>>> getAllClasses() {
        return ResponseEntity.ok(ApiResponse.success(classService.getAllClasses(), "Classes retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ClassResponse>> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(classService.getClassById(id), "Class retrieved successfully"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ClassResponse>> createClass(@Valid @RequestBody ClassRequest request) {
        return ResponseEntity.ok(ApiResponse.success(classService.createClass(request), "Class created successfully"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<ClassResponse>> updateClass(@PathVariable Long id, @Valid @RequestBody ClassRequest request) {
        return ResponseEntity.ok(ApiResponse.success(classService.updateClass(id, request), "Class updated successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Class deleted successfully"));
    }
}
