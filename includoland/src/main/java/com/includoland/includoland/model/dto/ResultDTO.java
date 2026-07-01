package com.includoland.includoland.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private Integer durationSec;
    private Float score;
    private Float mistake;
    private Float attentiveness;
    private String userUuid;
    private Long contentId;
}
