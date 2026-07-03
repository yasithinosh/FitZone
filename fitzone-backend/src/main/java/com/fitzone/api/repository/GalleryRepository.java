package com.fitzone.api.repository;

import com.fitzone.api.model.GalleryImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<GalleryImage, Long> {
}
