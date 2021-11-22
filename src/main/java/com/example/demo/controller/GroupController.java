package com.example.demo.controller;

import com.example.demo.dto.GroupDto;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public GroupDto getGroup(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

}
