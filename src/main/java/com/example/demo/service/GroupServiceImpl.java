package com.example.demo.service;

import com.example.demo.converter.CycleAvoidingMappingContext;
import com.example.demo.converter.GroupMapper;
import com.example.demo.dto.GroupDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {


    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CycleAvoidingMappingContext context;

    @Override
    public GroupDto getGroupById(Long id) {
        log.info("calling getGroupById");
        Group byId = groupRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("no group found"));
        return groupMapper.toDto(byId, context);
    }

    @Override
    public List<GroupDto> getAll() {
        log.info("calling getAll");
        return groupRepository.findAll()
                .stream()
                .map(g -> groupMapper.toDto(g, context))
                .collect(Collectors.toList());
    }

    @Override
    public Set<StudentDto> getStudentsByGroupId(Long id) {
        log.info("calling get students from group with id");
        return getGroupById(id).getStudents();
    }


    @Override
    public GroupDto saveGroup(GroupDto dto) {
        log.info("calling save group");
        Group group = groupMapper.toEntity(dto, context);
        return groupMapper.toDto(groupRepository.save(group), context);
    }

    @Override
    public GroupDto update(GroupDto dto, Long id) {
        log.info("calling update group");
        Group group1 = groupRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("no group found to update"));
        log.info("group found - {0}", group1.toString());
        Group group = groupMapper.toEntity(dto, context);
        group.setId(id);
        groupRepository.save(group);
        return groupMapper.toDto(group, context);
    }

    @Override
    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

}
