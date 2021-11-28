package com.example.demo.converter;

import com.example.demo.dto.GroupDto;
import com.example.demo.model.Group;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupConverter {

    public GroupDto toDto(Group entity) {
        GroupDto dto = new GroupDto();
        dto.setId(entity.getId());
        dto.setGroupNumber(entity.getGroupNumber());
        List<Long> lessonIdsList = entity.getLessons().stream()
                .map(Lesson::getId)
                .collect(Collectors.toList());
        List<Long> studentIdsList = entity.getStudents().stream()
                .map(Student::getId)
                .collect(Collectors.toList());
        dto.setStudentsIds(studentIdsList);
        dto.setLessonsIds(lessonIdsList);
        return dto;
    }

    public Group toEntity(GroupDto dto) {
        Group entity = new Group();
        entity.setId(dto.getId());
        entity.setGroupNumber(dto.getGroupNumber());
        return entity;
    }
}
