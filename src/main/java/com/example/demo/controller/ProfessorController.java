package com.example.demo.controller;

import com.example.demo.dto.LessonDto;
import com.example.demo.dto.ProfessorDto;
import com.example.demo.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/professors", produces = "application/json; charset=UTF-8")
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping("/create")
    public ProfessorDto create(@RequestBody @Valid ProfessorDto professorDto) {
        return (professorService.create(professorDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<ProfessorDto>> getAllProfessors() {
        return ResponseEntity.ok(professorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.findById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ProfessorDto> update(@RequestBody ProfessorDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(professorService.update(dto, id));
    }

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id) {
        professorService.delete(id);
    }

    @PostMapping("/{id}/addLesson")
    public ResponseEntity<ProfessorDto> addLesson(@PathVariable Long id, @RequestBody LessonDto dto) {
        return ResponseEntity.ok(professorService.addLesson(id, dto));
    }
}
