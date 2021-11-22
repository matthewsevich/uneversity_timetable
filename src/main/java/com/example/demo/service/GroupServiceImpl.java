package com.example.demo.service;

import com.example.demo.converter.CycleAvoidingMappingContext;
import com.example.demo.converter.GroupMapper;
import com.example.demo.dto.GroupDto;
import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final CycleAvoidingMappingContext context;

    @Override
    public GroupDto getGroupById(Long id) {
        if (id != null) {
            Group byId = groupRepository.getById(id);
            return groupMapper.toDto(byId, context);
        }
        return null;
    }
}
