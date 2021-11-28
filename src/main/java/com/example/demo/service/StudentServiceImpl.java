package com.example.demo.service;

import com.example.demo.converter.StudentConverter;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Student;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final GroupRepository groupRepository;
    private final StudentConverter converter;

    public StudentDto saveStudent(StudentDto dto) {
        log.info("calling save student");
        Student student = converter.toEntity(dto);
        student.setGroup(groupRepository.findById(dto.getGroupId()).orElseThrow(() -> new NoSuchEntityException("no such group found")));
        return converter.toDto(repository.save(student));
    }

    @Override
    public List<StudentDto> getAllStudents() {
        log.info("calling getAllStudents");
        return repository.findAll().stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("call delete student with id {}", id);
        repository.deleteById(id);
    }

    @Override
    public StudentDto getStudent(Long id) {
        log.info("call find student with id {}", id);
        Student found = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no student found"));
        return converter.toDto(found);
    }

    @Override
    public StudentDto update(StudentDto dto, Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no student found"));
        log.info("student found {}", student.toString());
        Student entity = converter.toEntity(dto);
        entity.setGroup(groupRepository.findById(dto.getGroupId()).orElseThrow(() -> new NoSuchEntityException("no such group found")));
        entity.setId(id);
        return converter.toDto(repository.save(entity));

    }
}
