package com.includoland.includoland.service.impl;

import com.includoland.includoland.model.entity.Notice;
import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.repository.NoticeRepository;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.service.NoticeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Notice createNotice(String title, String text, String specialistUuid, String childUuid) {
        UUID specialistId = UUID.fromString(specialistUuid);
        UUID childId = UUID.fromString(childUuid);
        User specialist = userRepository.findById(specialistId)
                .orElseThrow(() -> new EntityNotFoundException("Specialist not found with id: " + specialistUuid));
        User child = userRepository.findById(childId)
                .orElseThrow(() -> new EntityNotFoundException("Child not found with id: " + childUuid));

        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setText(text);
        notice.setSpecialist(specialist);
        notice.setChild(child);
        notice.setCreatedAt(LocalDateTime.now());
        notice.setUpdatedAt(LocalDateTime.now());

        return noticeRepository.save(notice);
    }

    @Override
    public List<Notice> getNoticesForChild(String childUuid) {
        return noticeRepository.findByChildUuid(childUuid);
    }
}
