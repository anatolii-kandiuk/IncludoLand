package com.includoland.includoland.repository;

import com.includoland.includoland.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    List<Notice> findBySpecialistUuid(String specialistUuid);

    List<Notice> findByChildUuid(String childUuid);
}
