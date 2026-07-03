package com.fitzone.api.service;

import com.fitzone.api.dto.request.ClassRequest;
import com.fitzone.api.dto.response.ClassResponse;
import com.fitzone.api.exception.ResourceNotFoundException;
import com.fitzone.api.mapper.ClassMapper;
import com.fitzone.api.model.GymClass;
import com.fitzone.api.model.Trainer;
import com.fitzone.api.repository.GymClassRepository;
import com.fitzone.api.repository.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassService {

    private final GymClassRepository classRepository;
    private final TrainerRepository trainerRepository;
    private final ClassMapper classMapper;

    public List<ClassResponse> getAllClasses() {
        return classRepository.findAll().stream()
                .map(classMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ClassResponse getClassById(Long id) {
        GymClass gymClass = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
        return classMapper.toResponse(gymClass);
    }

    @Transactional
    public ClassResponse createClass(ClassRequest request) {
        Trainer trainer = null;
        if (request.getTrainerId() != null) {
            trainer = trainerRepository.findById(request.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + request.getTrainerId()));
        }

        GymClass gymClass = GymClass.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .intensity(request.getIntensity())
                .trainer(trainer)
                .dayOfWeek(request.getDayOfWeek())
                .startTime(request.getStartTime())
                .durationMin(request.getDurationMin())
                .capacity(request.getCapacity())
                .imageUrl(request.getImageUrl())
                .build();

        return classMapper.toResponse(classRepository.save(gymClass));
    }

    @Transactional
    public ClassResponse updateClass(Long id, ClassRequest request) {
        GymClass gymClass = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));

        if (request.getTrainerId() != null) {
            Trainer trainer = trainerRepository.findById(request.getTrainerId())
                    .orElseThrow(() -> new ResourceNotFoundException("Trainer not found with id: " + request.getTrainerId()));
            gymClass.setTrainer(trainer);
        }

        gymClass.setName(request.getName());
        gymClass.setDescription(request.getDescription());
        gymClass.setCategory(request.getCategory());
        gymClass.setIntensity(request.getIntensity());
        gymClass.setDayOfWeek(request.getDayOfWeek());
        gymClass.setStartTime(request.getStartTime());
        gymClass.setDurationMin(request.getDurationMin());
        gymClass.setCapacity(request.getCapacity());
        gymClass.setImageUrl(request.getImageUrl());

        return classMapper.toResponse(classRepository.save(gymClass));
    }

    @Transactional
    public void deleteClass(Long id) {
        GymClass gymClass = classRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found with id: " + id));
        classRepository.delete(gymClass);
    }
}
