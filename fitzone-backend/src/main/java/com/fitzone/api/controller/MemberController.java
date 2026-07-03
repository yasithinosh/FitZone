package com.fitzone.api.controller;

import com.fitzone.api.dto.response.ApiResponse;
import com.fitzone.api.dto.response.DashboardResponse;
import com.fitzone.api.dto.response.MemberResponse;
import com.fitzone.api.model.Member;
import com.fitzone.api.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<MemberResponse>>> getAllMembers() {
        return ResponseEntity.ok(ApiResponse.success(memberService.getAllMembers(), "Members retrieved successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(memberService.getMemberById(id), "Member retrieved successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MemberResponse>> updateMember(@PathVariable Long id, @RequestBody Member updateData) {
        return ResponseEntity.ok(ApiResponse.success(memberService.updateMember(id, updateData), "Member updated successfully"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Member deleted successfully"));
    }

    @GetMapping("/{id}/dashboard")
    public ResponseEntity<ApiResponse<DashboardResponse>> getMemberDashboard(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(memberService.getMemberDashboard(id), "Dashboard retrieved successfully"));
    }
}
