package com.includoland.includoland.service.impl;

import com.includoland.includoland.model.entity.Content;
import com.includoland.includoland.model.entity.Result;
import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.repository.ContentRepository;
import com.includoland.includoland.repository.ResultRepository;
import com.includoland.includoland.repository.UserRepository;
import com.includoland.includoland.service.ResultService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Override
    @Transactional
    public Result saveResult(Result result, String userUuid, Long contentId) {
        User user = userRepository.findById(userUuid)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userUuid));
        Content content = contentRepository.findById(contentId)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + contentId));

        result.setUser(user);
        result.setContent(content);
        return resultRepository.save(result);
    }

    @Override
    public List<Result> getResultsByUser(String userUuid) {
        return resultRepository.findByUserUuidOrderByIdDesc(userUuid);
    }
}
