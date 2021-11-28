package com.example.demo.converter;

import com.example.demo.dto.ProfessorDto;
import com.example.demo.model.Lesson;
import com.example.demo.model.Professor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProfessorConverter {

    public ProfessorDto toDto(Professor entity) {
        ProfessorDto dto = new ProfessorDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setLessonIds(entity.getLessons().stream()
                .map(Lesson::getId)
                .collect(Collectors.toList()));
        return dto;
    }

    public Professor toEntity(ProfessorDto dto) {
        Professor entity = new Professor();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        return entity;
    }
}
