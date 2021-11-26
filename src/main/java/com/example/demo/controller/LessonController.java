package com.example.demo.controller;

import com.example.demo.dto.LessonDto;
import com.example.demo.service.LessonService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/groups", produces = "application/json; charset=UTF-8")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lesson) {
        return ResponseEntity.ok(lessonService.saveLesson(lesson));
    }

    @GetMapping()
    public List<LessonDto> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @DeleteMapping("/delete/{id}")
    public void delLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLesson(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> update(@RequestBody LessonDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(lessonService.updateLesson(dto, id));
    }
}
