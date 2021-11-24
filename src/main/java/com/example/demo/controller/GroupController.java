package com.example.demo.controller;

import com.example.demo.dto.GroupDto;
import com.example.demo.dto.StudentDto;
import com.example.demo.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/groups", produces = "application/json; charset=UTF-8")
public class GroupController {

    private final GroupService groupService;

    @Operation(summary = "get group by id", description = "get group")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success",
                    content = @Content(schema = @Schema(implementation = GroupDto.class))),
            @ApiResponse(responseCode = "404", description = "group not found")})
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }

    @GetMapping("/{id}/students")
    public ResponseEntity<Set<StudentDto>> getStudentsFromGroup(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getStudentsByGroupId(id));
    }

    @GetMapping()
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAll());
    }

    @PostMapping()
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto dto) {
        return ResponseEntity.ok(groupService.saveGroup(dto));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<GroupDto> updateGroup(@RequestBody GroupDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(groupService.update(dto, id));
    }

    @DeleteMapping("/{id}/delete")
    public void deleteGroup(Long id) {
        groupService.deleteGroup(id);
    }

}
