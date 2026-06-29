package com.includoland.includoland.repository;

import com.includoland.includoland.model.entity.Content;
import com.includoland.includoland.model.enums.TypeOfContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    List<Content> findByTypeOfContent(TypeOfContent typeOfContent);

    List<Content> findByIsPublicTrue();
}
