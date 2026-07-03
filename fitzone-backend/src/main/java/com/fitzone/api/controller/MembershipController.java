package com.fitzone.api.controller;

import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.model.Membership;
import com.fitzone.api.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Membership>>> getAllMemberships() {
        return ResponseEntity.ok(ApiResponse.success(membershipService.getAllMemberships(), "Memberships retrieved successfully"));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Membership>> createMembership(@RequestBody Membership membership) {
        return ResponseEntity.ok(ApiResponse.success(membershipService.createMembership(membership), "Membership created successfully"));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Membership>> updateMembership(@PathVariable Long id, @RequestBody Membership updateData) {
        return ResponseEntity.ok(ApiResponse.success(membershipService.updateMembership(id, updateData), "Membership updated successfully"));
    }
}
