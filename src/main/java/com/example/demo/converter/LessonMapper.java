package com.example.demo.converter;

import com.example.demo.dto.LessonDto;
import com.example.demo.model.Lesson;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(uses = GroupMapper.class, componentModel = "spring")
public interface LessonMapper {

    LessonDto toDto(Lesson entity,@Context CycleAvoidingMappingContext context);

    Lesson toEntity(LessonDto dto,@Context CycleAvoidingMappingContext context);
}
