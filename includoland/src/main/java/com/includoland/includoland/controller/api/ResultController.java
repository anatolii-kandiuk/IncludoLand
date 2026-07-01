package com.includoland.includoland.controller.api;

import com.includoland.includoland.model.dto.ResultDTO;
import com.includoland.includoland.model.entity.Result;
import com.includoland.includoland.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/results")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping
    public ResponseEntity<String> saveResult(@RequestBody ResultDTO resultDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Перевірка авторизації: якщо користувач не автентифікований або є anonymousUser
        if (authentication == null 
            || !authentication.isAuthenticated() 
            || "anonymousUser".equals(authentication.getPrincipal())) {
            // Гостьовий режим: просто повертаємо успіх без збереження
            return ResponseEntity.ok("{\"status\": \"guest_mode\", \"message\": \"Results not saved in guest mode\"}");
        }

        // Авторизований користувач: зберігаємо результат
        String userEmail = authentication.getName();
        try {
            Result result = new Result();
            result.setDurationSec(resultDTO.getDurationSec());
            result.setScore(resultDTO.getScore());
            result.setMistake(resultDTO.getMistake());
            result.setAttentiveness(resultDTO.getAttentiveness());

            resultService.saveResult(result, resultDTO.getUserUuid(), resultDTO.getContentId());
            return ResponseEntity.ok("{\"status\": \"success\", \"message\": \"Result saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"status\": \"error\", \"message\": \"" + e.getMessage() + "\"}");
        }
    }
}
