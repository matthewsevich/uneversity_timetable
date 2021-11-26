package com.example.demo.converter;

import com.example.demo.dto.ProfessorDto;
import com.example.demo.model.Professor;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(uses = LessonMapper.class, componentModel = "spring")
public interface ProfessorMapper {

    ProfessorDto toDto(Professor entity, @Context CycleAvoidingMappingContext context);

    Professor toEntity(ProfessorDto dto, @Context CycleAvoidingMappingContext context);
}
