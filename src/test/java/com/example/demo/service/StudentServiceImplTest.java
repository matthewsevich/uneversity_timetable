package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.converter.StudentConverter;
import com.example.demo.dto.StudentDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:ApplicationTest.properties")
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class StudentServiceImplTest {

    @Resource
    StudentRepository repository;
    @Resource
    StudentService service;
    @Resource
    StudentConverter converter;


    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void saveStudent() {
        StudentDto studentDto = createStudent();
        service.saveStudent(studentDto);
        Student next = repository.findAll().iterator().next();
        assertEquals(studentDto.getFirstName(), next.getFirstName());
    }

    @Test
    void exceptionTest() {
        if (repository.findAll().size() == 0L) {
            assertThrows(NoSuchEntityException.class, () -> service.getStudent(1L));
        } else fail();
    }

    @Test
    void getAllStudents() {
        StudentDto studentDto = createStudent();
        service.saveStudent(studentDto);
        assertEquals(1, service.getAllStudents().size());
        service.saveStudent(studentDto);
        assertEquals(2, service.getAllStudents().size());
        service.saveStudent(studentDto);
        assertEquals(3, service.getAllStudents().size());
    }

    @Test
    void deleteStudent() {
        assertEquals(0, service.getAllStudents().size());
        StudentDto studentDto = createStudent();
        service.saveStudent(studentDto);
        assertEquals(1, service.getAllStudents().size());
        Long id = repository.findAll().iterator().next().getId();
        service.deleteStudent(id);
        assertEquals(0, service.getAllStudents().size());
    }

    @Test
    void getStudent() {
        StudentDto studentDto = createStudent();
        assertEquals(0, service.getAllStudents().size());
        service.saveStudent(studentDto);
        assertEquals(1, service.getAllStudents().size());
        Long id = repository.findAll().iterator().next().getId();
        StudentDto fromDb = service.getStudent(id);
        assertEquals(studentDto.getLastName(), fromDb.getLastName());
        assertEquals(studentDto.getFirstName(), fromDb.getFirstName());
    }

    @Test
    void update() {
        StudentDto dto = createStudent();
        repository.save(converter.toEntity(dto));
        assertEquals(1, repository.findAll().size());
        Long id = repository.findAll().iterator().next().getId();

        StudentDto fromDb = service.getStudent(id);
        assertEquals(fromDb.getFirstName(), dto.getFirstName());
        dto.setFirstName("Ivan");
        service.update(dto, fromDb.getId());
        StudentDto updated = service.getStudent(id);
        assertEquals(dto.getFirstName(), updated.getFirstName());
        assertEquals(1, repository.findAll().size());
    }

    private StudentDto createStudent() {
        StudentDto dto = new StudentDto();
        dto.setFirstName("alex");
        dto.setLastName("petrov");
        return dto;
    }
}