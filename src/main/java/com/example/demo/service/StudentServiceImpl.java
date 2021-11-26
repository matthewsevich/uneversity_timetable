package com.example.demo.service;

import com.example.demo.converter.CycleAvoidingMappingContext;
import com.example.demo.converter.StudentMapper;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper studentMapper;
    private final CycleAvoidingMappingContext context;


    public StudentDto saveStudent(StudentDto dto) {
        Student student = studentMapper.toEntity(dto, context);
        return studentMapper.toDto(repository.save(student), context);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<StudentDto> dtos = new ArrayList<>();
        List<Student> students = repository.findAll();
        for (Student s : students) {
            dtos.add(studentMapper.toDto(s, context));
        }
        return dtos;
    }

    @Override
    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }

    @Override
    public StudentDto getStudent(Long id) {
        Optional<Student> studentOptional = repository.findById(id);
        if (studentOptional.isEmpty()) {
            return null;
        }
        Student student = studentOptional.get();
        return studentMapper.toDto(student, context);
    }

    @Override
    public StudentDto update(StudentDto dto, Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no student found"));
        log.info("student found {}", student.toString());
        Student entity = studentMapper.toEntity(dto, context);
        entity.setId(id);
        return studentMapper.toDto(repository.save(entity), context);

    }
}
