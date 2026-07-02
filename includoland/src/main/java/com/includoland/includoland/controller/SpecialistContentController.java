package com.includoland.includoland.controller;

import com.includoland.includoland.model.entity.Content;
import com.includoland.includoland.model.entity.User;
import com.includoland.includoland.model.enums.TypeOfContent;
import com.includoland.includoland.service.ContentService;
import com.includoland.includoland.service.StorageService;
import com.includoland.includoland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/specialist/content")
@RequiredArgsConstructor
public class SpecialistContentController {

    private final ContentService contentService;
    private final StorageService storageService;
    private final UserService userService;

    @GetMapping("/add")
    public String showAddContentForm(Model model) {
        model.addAttribute("content", new Content());
        model.addAttribute("contentTypes", TypeOfContent.values());
        return "specialist/add_content";
    }

    @PostMapping("/add")
    public String addContent(@ModelAttribute("content") Content content,
                             @RequestParam("picture") MultipartFile picture,
                             @RequestParam("audio") MultipartFile audio,
                             Principal principal) {
        try {
            if (!picture.isEmpty()) {
                String pictureUrl = storageService.uploadFile(picture, "content-images");
                content.setPictureLink(pictureUrl);
            }
            if (!audio.isEmpty()) {
                String audioUrl = storageService.uploadFile(audio, "content-audio");
                content.setAudioLink(audioUrl);
            }

            User author = userService.getUserByEmail(principal.getName()); 
            content.setAuthor(author);
            content.setCreatedAt(LocalDateTime.now());
            content.setUpdatedAt(LocalDateTime.now());

            contentService.saveContent(content);

        } catch (IOException e) {
            return "redirect:/specialist/content/add?error=upload_failed";
        }

        // Повертаємо на дашборд спеціаліста з параметром успіху
        return "redirect:/specialist/dashboard?success=content_added";
    }
}