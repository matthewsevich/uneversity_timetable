package com.example.demo.service;

import com.example.demo.dto.GroupDto;
import com.example.demo.dto.StudentDto;

import java.util.List;
import java.util.Set;

public interface GroupService {

    GroupDto getGroupById(Long id);

    List<GroupDto> getAll();

    Set<StudentDto> getStudentsByGroupId(Long id);

    GroupDto saveGroup(GroupDto dto);

    GroupDto update(GroupDto dto, Long id);

    void deleteGroup(Long id);
}
