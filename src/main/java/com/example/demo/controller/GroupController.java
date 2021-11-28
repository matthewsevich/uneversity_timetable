package com.example.demo.controller;

import com.example.demo.dto.GroupDto;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/groups", produces = "application/json; charset=UTF-8")
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto dto) {
        return ResponseEntity.ok(groupService.saveGroup(dto));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(groupService.update(dto, id));
    }

    @DeleteMapping("/{id}/delete")
    public void deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
    }

}
