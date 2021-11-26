package com.example.demo.service;

import com.example.demo.dto.LessonDto;

import java.util.List;

public interface LessonService {

    List<LessonDto> getAllLessons();

    LessonDto findById(Long id);

    LessonDto updateLesson(LessonDto dto, Long id);

    void deleteLesson(Long id);

    LessonDto saveLesson(LessonDto dto);
}
