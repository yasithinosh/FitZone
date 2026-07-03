package com.fitzone.api.service;

import com.fitzone.api.dto.request.TrainerRequest;
import com.fitzone.api.dto.response.TrainerResponse;
import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.mapper.TrainerMapper;
import com.fitzone.api.model.Role;
import com.fitzone.api.model.Trainer;
import com.fitzone.api.model.User;
import com.fitzone.api.repository.TrainerRepository;
import com.fitzone.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;
    private final TrainerMapper trainerMapper;
    private final PasswordEncoder passwordEncoder;

    public List<TrainerResponse> getAllTrainers() {
        return trainerRepository.findAll().stream()
                .map(trainerMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TrainerResponse getTrainerById(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));
        return trainerMapper.toResponse(trainer);
    }

    @Transactional
    public TrainerResponse createTrainer(TrainerRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already taken!");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_TRAINER)
                .build();

        User savedUser = userRepository.save(user);

        Trainer trainer = Trainer.builder()
                .user(savedUser)
                .bio(request.getBio())
                .speciality(request.getSpeciality())
                .photoUrl(request.getPhotoUrl())
                .build();

        return trainerMapper.toResponse(trainerRepository.save(trainer));
    }

    @Transactional
    public TrainerResponse updateTrainer(Long id, TrainerRequest request) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));

        trainer.setBio(request.getBio());
        trainer.setSpeciality(request.getSpeciality());
        trainer.setPhotoUrl(request.getPhotoUrl());
        
        // Update user part if needed (e.g. name, email)

        return trainerMapper.toResponse(trainerRepository.save(trainer));
    }

    @Transactional
    public void deleteTrainer(Long id) {
        Trainer trainer = trainerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + id));
        trainerRepository.delete(trainer);
        // Note: You may want to handle User deletion depending on your business rules
    }
}
