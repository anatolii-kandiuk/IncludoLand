package com.includoland.includoland.service;

import com.includoland.includoland.model.entity.Content;
import com.includoland.includoland.model.enums.TypeOfContent;

import java.util.List;

public interface ContentService {
    List<Content> getAllPublicContent();

    List<Content> getContentByType(TypeOfContent type);
}
