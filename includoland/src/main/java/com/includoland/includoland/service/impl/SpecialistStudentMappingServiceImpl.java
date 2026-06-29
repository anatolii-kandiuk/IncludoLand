package com.includoland.includoland.service.impl;

import com.includoland.includoland.model.entity.SpecialistStudentMapping;
import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.model.enums.Role;
import com.includoland.includoland.repository.SpecialistStudentMappingRepository;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.service.SpecialistStudentMappingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SpecialistStudentMappingServiceImpl implements SpecialistStudentMappingService {
    private final SpecialistStudentMappingRepository mappingRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SpecialistStudentMapping assignChildToSpecialist(String childUuid, String specialistUuid) {
        User child = userRepository.findById(childUuid)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id: " + childUuid));
        User specialist = userRepository.findById(specialistUuid)
                .orElseThrow(() -> new EntityNotFoundException("Specialist not found with id: " + specialistUuid));

        if (child.getRole() != Role.CHILD) {
            throw new IllegalArgumentException("The provided user is not a child: " + childUuid);
        }
        if (specialist.getRole() != Role.SPECIALIST) {
            throw new IllegalArgumentException("The provided user is not a specialist: " + specialistUuid);
        }

        SpecialistStudentMapping mapping = new SpecialistStudentMapping();
        mapping.setChild(child);
        mapping.setSpecialist(specialist);
        mapping.setCreatedAt(LocalDateTime.now());

        return mappingRepository.save(mapping);
    }
}
