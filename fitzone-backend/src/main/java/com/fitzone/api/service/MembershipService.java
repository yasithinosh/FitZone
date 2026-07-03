package com.fitzone.api.service;

import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.model.Membership;
import com.fitzone.api.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public List<Membership> getAllMemberships() {
        return membershipRepository.findAll();
    }

    public Membership createMembership(Membership membership) {
        return membershipRepository.save(membership);
    }

    public Membership updateMembership(Long id, Membership updateData) {
        Membership membership = membershipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membership not found with id: " + id));
        
        membership.setPrice(updateData.getPrice());
        membership.setDurationDays(updateData.getDurationDays());
        membership.setFeatures(updateData.getFeatures());
        
        return membershipRepository.save(membership);
    }
}
