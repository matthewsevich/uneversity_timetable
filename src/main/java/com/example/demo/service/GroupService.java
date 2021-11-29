package com.example.demo.service;

import com.example.demo.dto.GroupDto;

import java.util.List;

public interface GroupService {

    GroupDto getGroupById(Long id);

    List<GroupDto> getAll();

    GroupDto saveGroup(GroupDto dto);

    GroupDto update(GroupDto dto, Long id);

    void deleteGroup(Long id);

    void addLessonToGroup(Long id, Long lessonId);
}
