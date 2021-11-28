package com.example.demo.controller;

import com.example.demo.dto.LessonDto;
import com.example.demo.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    @PostMapping("/create")
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lesson) {
        return ResponseEntity.ok(lessonService.saveLesson(lesson));
    }

    @GetMapping("/")
    public List<LessonDto> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @DeleteMapping("/{id}/delete")
    public void delLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLesson(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.findById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<LessonDto> update(LessonDto dto, @PathVariable Long id) {
        return ResponseEntity.ok(lessonService.updateLesson(dto, id));
    }

    @GetMapping("/{id}/{day}")
    public ResponseEntity<List<LessonDto>> getStudentsLessonsByDay(@PathParam("id") Long id, @PathParam("day") Integer day) {
        return ResponseEntity.ok(lessonService.getStudentsLessons(id, day));
    }
}
