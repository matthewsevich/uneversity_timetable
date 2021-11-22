package com.example.demo.service;

import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {

    List<StudentDto> getAllStudents();
    Student saveStudent(StudentDto dto);
    void deleteStudent(Long id);
    StudentDto getStudent(Long id);
    ResponseEntity<StudentDto> update(StudentDto dto, Long id);

}
