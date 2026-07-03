package com.fitzone.api.controller;

import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.model.GalleryImage;
import com.fitzone.api.service.GalleryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/gallery")
@RequiredArgsConstructor
public class GalleryController {

    private final GalleryService galleryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<GalleryImage>>> getAllImages() {
        return ResponseEntity.ok(ApiResponse.success(galleryService.getAllImages(), "Images retrieved successfully"));
    }

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<GalleryImage>> uploadImage(@RequestParam("file") MultipartFile file,
                                                                 @RequestParam(value = "caption", required = false) String caption) {
        return ResponseEntity.ok(ApiResponse.success(galleryService.uploadImage(file, caption), "Image uploaded successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteImage(@PathVariable Long id) {
        galleryService.deleteImage(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Image deleted successfully"));
    }
}
