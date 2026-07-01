package com.includoland.includoland.controller;

import com.includoland.includoland.model.entity.Content;
import com.includoland.includoland.model.enums.TypeOfContent;
import com.includoland.includoland.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/activities")
public class ContentController {
    private final ContentService contentService;

    @GetMapping("/articulation")
    public String articulation(Model model) {
        List<Content> contentList = contentService.getContentByType(TypeOfContent.ARTICULATION);

        if (contentList == null || contentList.isEmpty()) {
            Content dummy = new Content();
            dummy.setTitle("Артикуляційна вправа");
            dummy.setTextData("Тестова картка для демонстрації сторінки артикуляції.");
            dummy.setTypeOfContent(TypeOfContent.ARTICULATION);
            dummy.setPictureLink("https://images.unsplash.com/photo-1516321497487-e288fb19713f?auto=format&fit=crop&w=800&q=80");
            dummy.setPublic(true);
            dummy.setCreatedAt(LocalDateTime.now());
            dummy.setUpdatedAt(LocalDateTime.now());
            contentList = new ArrayList<>();
            contentList.add(dummy);
        }

        model.addAttribute("contents", contentList);
        return "articulation";
    }
}
