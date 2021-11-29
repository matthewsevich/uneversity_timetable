package com.example.demo.converter;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {

    public StudentDto toDto(Student entity) {
        if (entity == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        if (entity.getGroup() != null) {
            dto.setGroupId(entity.getGroup().getId());
        }
        return dto;
    }

    public Student toEntity(StudentDto dto) {
        Student entity = new Student();
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        return entity;
    }
}
