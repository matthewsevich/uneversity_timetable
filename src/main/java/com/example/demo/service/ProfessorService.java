package com.example.demo.service;

import com.example.demo.dto.LessonDto;
import com.example.demo.dto.ProfessorDto;

import java.util.List;

public interface ProfessorService {

    ProfessorDto create(ProfessorDto professor);

    List<ProfessorDto> findAll();

    ProfessorDto update(ProfessorDto professor, Long id);

    ProfessorDto findById(Long id);

    void delete(Long id);

    ProfessorDto addLesson(Long id, LessonDto dto);
}
