package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.converter.LessonConverter;
import com.example.demo.converter.ProfessorConverter;
import com.example.demo.dto.LessonDto;
import com.example.demo.dto.ProfessorDto;
import com.example.demo.model.Lesson;
import com.example.demo.model.Professor;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.ProfessorRepository;
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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:ApplicationTest.properties")
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class ProfessorServiceImplTest {

    @Resource
    ProfessorService service;
    @Resource
    ProfessorRepository repository;
    @Resource
    ProfessorConverter converter;
    @Resource
    LessonRepository lessonRepository;
    @Resource
    LessonConverter lessonConverter;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void create() {
        ProfessorDto professor = createProfessor();
        assertEquals(0, repository.findAll().size());
        service.create(professor);
        assertEquals(1, repository.findAll().size());
        Professor byId = repository.findAll().iterator().next();
        assertEquals(professor.getFirstName(), byId.getFirstName());
    }

    @Test
    void findAll() {
        ProfessorDto professor = createProfessor();
        service.create(professor);
        assertEquals(1, service.findAll().size());
        service.create(professor);
        assertEquals(2, service.findAll().size());
    }

    @Test
    void update() {
        ProfessorDto professor = createProfessor();
        repository.save(converter.toEntity(professor));
        assertEquals(1, repository.findAll().size());
        Professor entity = repository.findAll().iterator().next();
        assertEquals(entity.getFirstName(), professor.getFirstName());
        professor.setFirstName("mister");
        Long id = entity.getId();
        service.update(professor, id);
        assertEquals(1, repository.findAll().size());
        assertEquals(professor.getFirstName(), repository.findAll().iterator().next().getFirstName());
    }

    @Test
    void findById() {
        ProfessorDto professor = createProfessor();
        repository.save(converter.toEntity(professor));
        assertEquals(1, service.findAll().size());
        Long id = repository.findAll().iterator().next().getId();
        ProfessorDto byId = service.findById(id);
        assertEquals(professor.getFirstName(), byId.getFirstName());
    }

    @Test
    void delete() {
        assertEquals(0, service.findAll().size());
        ProfessorDto professor = createProfessor();
        service.create(professor);
        assertEquals(1, service.findAll().size());
        service.create(professor);
        Long id = repository.findAll().iterator().next().getId();

        service.delete(id);
        assertEquals(1, service.findAll().size());
    }

    @Test
    void addLesson() {
        ProfessorDto professor = createProfessor();
        repository.save(converter.toEntity(professor));
        assertEquals(1, service.findAll().size());
        Long id = repository.findAll().iterator().next().getId();
        Professor byId = repository.getById(id);
        assertEquals(0, byId.getLessons().size());
        byId.setLessons(new ArrayList<>());
        repository.save(byId);
        lessonRepository.save(getLesson());
        assertEquals(0, repository.getById(id).getLessons().size());
        service.addLesson(id, lessonConverter.toDto(lessonRepository.findAll().iterator().next()));

        assertEquals(1, repository.getById(id).getLessons().size());


    }

    private ProfessorDto createProfessor() {
        ProfessorDto dto = new ProfessorDto();
        dto.setFirstName("doctor");
        dto.setLastName("who");
        return dto;
    }

    private Lesson getLesson() {
        Lesson lesson = new Lesson();
        lesson.setTitle("java");
        lesson.setTime("10pm");
        return lesson;
    }

    private LessonDto createLessonDto() {
        LessonDto dto = new LessonDto();
        dto.setDay(1);
        dto.setTime("1pm");
        dto.setTitle("java");
        return dto;
    }

}