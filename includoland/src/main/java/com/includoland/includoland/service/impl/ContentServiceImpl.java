package com.includoland.includoland.service.impl;

import com.includoland.includoland.model.entity.Content;
import com.includoland.includoland.model.enums.TypeOfContent;
import com.includoland.includoland.repository.ContentRepository;
import com.includoland.includoland.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;

    @Override
    public List<Content> getAllPublicContent() {
        return contentRepository.findByIsPublicTrue();
    }

    @Override
    public List<Content> getContentByType(TypeOfContent type) {
        return contentRepository.findByTypeOfContent(type);
    }

    @Override
    public Content savContent(Content content) {
        return contentRepository.save(content);
    }
}
