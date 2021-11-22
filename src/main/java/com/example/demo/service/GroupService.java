package com.example.demo.service;

import com.example.demo.dto.GroupDto;
import com.example.demo.model.Group;

public interface GroupService {

    GroupDto getGroupById(Long id);
}
