package com.includoland.includoland.service;

import com.includoland.includoland.model.entity.Notice;

import java.util.List;

public interface NoticeService {
    Notice createNotice(String title, String text, String specialistUuid, String childUuid);

    List<Notice> getNoticesForChild(String childUuid);
}
