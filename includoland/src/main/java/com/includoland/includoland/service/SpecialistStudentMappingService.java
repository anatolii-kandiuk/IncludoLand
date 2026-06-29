package com.includoland.includoland.service;

import com.includoland.includoland.model.entity.SpecialistStudentMapping;

public interface SpecialistStudentMappingService {
    SpecialistStudentMapping assignChildToSpecialist(String childUuid, String specialistUuid);
}
