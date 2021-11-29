package com.example.demo.converter;

import com.example.demo.dto.LessonDto;
import com.example.demo.model.Group;
import com.example.demo.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LessonConverter {

    public LessonDto toDto(Lesson entity) {
        if (entity == null) {
            return null;
        }
        LessonDto dto = new LessonDto();
        dto.setId(entity.getId());
        dto.setDay(entity.getDay());
        dto.setTime(entity.getTime());
        dto.setTitle(entity.getTitle());
        dto.setGroupsIds(entity.getGroups().stream()
                .map(Group::getId)
                .collect(Collectors.toList()));
        if (entity.getProfessor() != null) {
            dto.setProfessorId(entity.getProfessor().getId());
        }
        return dto;
    }

    public Lesson toEntity(LessonDto dto) {
        Lesson entity = new Lesson();
        entity.setDay(dto.getDay());
        entity.setTime(dto.getTime());
        entity.setTitle(dto.getTitle());

        return entity;
    }

}
