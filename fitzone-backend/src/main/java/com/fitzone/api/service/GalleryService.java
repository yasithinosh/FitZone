package com.fitzone.api.service;

import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.model.GalleryImage;
import com.fitzone.api.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;
    private final CloudinaryService cloudinaryService;

    public List<GalleryImage> getAllImages() {
        return galleryRepository.findAll();
    }

    @Transactional
    public GalleryImage uploadImage(MultipartFile file, String caption) {
        try {
            Map uploadResult = cloudinaryService.uploadFile(file);
            String imageUrl = uploadResult.get("url").toString();
            String publicId = uploadResult.get("public_id").toString();

            GalleryImage galleryImage = GalleryImage.builder()
                    .imageUrl(imageUrl)
                    .publicId(publicId)
                    .caption(caption)
                    .build();

            return galleryRepository.save(galleryImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    @Transactional
    public void deleteImage(Long id) {
        GalleryImage image = galleryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image not found"));

        try {
            cloudinaryService.deleteFile(image.getPublicId());
            galleryRepository.delete(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image from Cloudinary", e);
        }
    }
}
