package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.converter.LessonConverter;
import com.example.demo.dto.LessonDto;
import com.example.demo.model.Group;
import com.example.demo.model.Lesson;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.LessonRepository;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:ApplicationTest.properties")
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class LessonServiceImplTest {

    @Resource
    LessonService service;
    @Resource
    LessonRepository repository;
    @Resource
    LessonConverter converter;
    @Resource
    StudentRepository studentRepository;

    @AfterEach
    void tearUp() {
        repository.deleteAll();
    }

    @Test
    void getAllLessons() {
        LessonDto dto = createDto();
        repository.save(converter.toEntity(dto));
        repository.save(converter.toEntity(dto));
        assertEquals(2L, service.getAllLessons().size());
    }

    @Test
    void findById() {
        LessonDto dto = createDto();
        repository.save(converter.toEntity(dto));

        assertEquals(1L, service.getAllLessons().size());
        LessonDto next = service.getAllLessons().iterator().next();
        Long id = service.getAllLessons().iterator().next().getId();
        assertEquals(dto.getDay(), next.getDay());
        LessonDto byId = service.findById(id);
        assertEquals(byId.getTitle(), dto.getTitle());
    }

    @Test
    void updateLesson() {
        LessonDto dto = createDto();
        repository.save(converter.toEntity(dto));
        assertEquals(repository.findAll().size(), 1);
        Lesson lesson = repository.findAll().iterator().next();
        assertEquals(lesson.getTitle(), dto.getTitle());
        dto.setTitle("math");
        service.updateLesson(dto, lesson.getId());
        assertEquals(1, repository.findAll().size());
        assertEquals(repository.findAll().iterator().next().getTitle(), dto.getTitle());
    }

    @Test
    void deleteLesson() {
        LessonDto dto = createDto();
        assertEquals(0L, repository.findAll().size());
        repository.save(converter.toEntity(dto));
        assertEquals(repository.findAll().size(), 1L);
        Long id = repository.findAll().iterator().next().getId();
        service.deleteLesson(id);
        assertEquals(repository.findAll().size(), 0);
    }

    @Test
    void saveLesson() {
        LessonDto dto = createDto();
        assertEquals(0, repository.findAll().size());
        service.saveLesson(dto);
        Lesson next = repository.findAll().iterator().next();
        assertEquals(next.getTitle(), dto.getTitle());
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void getStudentsLessons() {
        saveStudentWithLessons();
        assertEquals(1, studentRepository.findAll().size());
        Long id = studentRepository.findAll().iterator().next().getId();
        List<LessonDto> dayOne = service.getStudentsLessons(id, 1);
        assertEquals(1, dayOne.size());
        List<LessonDto> dayTwo = service.getStudentsLessons(id, 2);
        assertEquals(1, dayTwo.size());
        List<LessonDto> dayThree = service.getStudentsLessons(id, 3);
        assertEquals(1, dayThree.size());
    }

    private LessonDto createDto() {
        LessonDto dto = new LessonDto();
        dto.setDay(1);
        dto.setTime("1pm");
        dto.setTitle("java");
        return dto;
    }

    private void saveStudentWithLessons() {
        Student student1 = new Student();
        student1.setFirstName("student 1");
        student1.setLastName("surname 1");
        Group group = new Group();
        group.setLessons(new ArrayList<>());

        List<Student> students = new ArrayList<>();
        students.add(student1);
        group.setGroupNumber("911");
        group.setStudents(students);
        student1.setGroup(group);

        Lesson lesson1 = getLesson(group, 1);
        Lesson lesson2 = getLesson(group, 2);
        Lesson lesson3 = getLesson(group, 3);
        Professor professor = new Professor();
        professor.setFirstName("doctor");
        lesson1.setProfessor(professor);
        lesson2.setProfessor(professor);
        lesson3.setProfessor(professor);
        studentRepository.save(student1);
    }

    private Lesson getLesson(Group group, Integer day) {
        Lesson lesson1 = new Lesson();
        List<Lesson> lessons = group.getLessons();
        lessons.add(lesson1);
        group.setLessons(lessons);
        lesson1.setTitle("java");
        lesson1.setTime("10pm");
        lesson1.setDay(day);
        return lesson1;
    }
}