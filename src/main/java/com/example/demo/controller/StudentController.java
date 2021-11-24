package com.example.demo.controller;

import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/students", produces = "application/json; charset=UTF-8")
public class StudentController {

    private final StudentService service;

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto student) {
        return ResponseEntity.ok(service.saveStudent(student));
    }

    @GetMapping()
    public List<StudentDto> getAllStudents() {
        return service.getAllStudents();
    }


    @DeleteMapping("/delete/{id}")
    public void delStudents(@PathVariable Long id) {
        service.deleteStudent(id);
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> update(@RequestBody StudentDto dto, @PathVariable Long id) {
        return service.update(dto, id);
    }
}
