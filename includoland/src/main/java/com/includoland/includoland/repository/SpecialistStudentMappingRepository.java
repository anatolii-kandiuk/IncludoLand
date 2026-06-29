package com.includoland.includoland.repository;

import com.includoland.includoland.model.entity.SpecialistStudentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpecialistStudentMappingRepository extends JpaRepository<SpecialistStudentMapping, String> {
    List<SpecialistStudentMapping> findBySpecialistUuid(String specialistUuid);

    Optional<SpecialistStudentMapping> findByChildUuid(String childUuid);
}
