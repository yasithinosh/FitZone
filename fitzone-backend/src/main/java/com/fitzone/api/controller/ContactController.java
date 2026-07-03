package com.fitzone.api.controller;

import com.fitzone.api.dto.request.ContactRequest;
import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.model.Contact;
import com.fitzone.api.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public ResponseEntity<ApiResponse<Contact>> submitMessage(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(ApiResponse.success(contactService.submitContactMessage(request), "Message sent successfully"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<Contact>>> getAllMessages() {
        return ResponseEntity.ok(ApiResponse.success(contactService.getAllMessages(), "Messages retrieved successfully"));
    }

    @PutMapping("/{id}/read")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Contact>> markAsRead(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(contactService.markAsRead(id), "Message marked as read"));
    }
}
