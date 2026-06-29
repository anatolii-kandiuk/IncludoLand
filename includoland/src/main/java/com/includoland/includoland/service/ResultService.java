package com.includoland.includoland.service;

import com.includoland.includoland.model.entity.Result;

import java.util.List;

public interface ResultService {
    Result saveResult(Result result, String userUuid, Long contentId);

    List<Result> getResultsByUser(String userUuid);
}
