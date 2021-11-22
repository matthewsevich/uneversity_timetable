package com.example.demo.converter;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(uses = GroupMapper.class, componentModel = "spring")
public interface StudentMapper {

    StudentDto toDto(Student entity,@Context CycleAvoidingMappingContext context);

    Student toEntity(StudentDto dto,@Context CycleAvoidingMappingContext context);
}
