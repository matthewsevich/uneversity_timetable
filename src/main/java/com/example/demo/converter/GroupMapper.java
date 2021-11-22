package com.example.demo.converter;

import com.example.demo.dto.GroupDto;
import com.example.demo.model.Group;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(uses = {StudentMapper.class, LessonMapper.class}, componentModel = "spring")
public interface GroupMapper {

    GroupDto toDto(Group entity,@Context CycleAvoidingMappingContext context);

    Group toEntity(GroupDto dto,@Context CycleAvoidingMappingContext context);
}
