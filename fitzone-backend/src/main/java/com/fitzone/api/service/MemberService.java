package com.fitzone.api.service;

import com.fitzone.api.dto.response.DashboardResponse;
import com.fitzone.api.dto.response.MemberResponse;
import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.mapper.MemberMapper;
import com.fitzone.api.model.Member;
import com.fitzone.api.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MemberResponse getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        return memberMapper.toResponse(member);
    }

    @Transactional
    public MemberResponse updateMember(Long id, Member updateData) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        
        // Currently just updating status as an example. Other fields can be mapped based on DTO
        if (updateData.getStatus() != null) {
            member.setStatus(updateData.getStatus());
        }

        return memberMapper.toResponse(memberRepository.save(member));
    }

    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    public DashboardResponse getMemberDashboard(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));

        // Stubbed data for dashboard
        return DashboardResponse.builder()
                .memberName(member.getUser().getName())
                .membershipType(member.getMembership() != null ? member.getMembership().getType().name() : "NONE")
                .expiryDate(member.getExpiryDate())
                .totalBookings(0)
                .classesThisMonth(0)
                .currentStreak(0)
                .daysLeft(0)
                .upcomingBookings(Collections.emptyList())
                .build();
    }
}
