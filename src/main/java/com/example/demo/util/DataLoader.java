package com.example.demo.util;

import com.example.demo.model.Group;
import com.example.demo.model.Lesson;
import com.example.demo.model.Professor;
import com.example.demo.model.Student;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class DataLoader implements ApplicationRunner {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @Override
    public void run(ApplicationArguments args) {
        for (int i = 0; i < 5; i++) {
            fillDb(i, String.valueOf(i));
        }
        boolean present = groupRepository.findById(1L).isPresent();
        if (present) {
            Group byId = groupRepository.getById(1L);
            for (int i = 1; i < 6; i++) {
                createLesson(byId);
                groupRepository.save(byId);
                groupService.addLessonToGroup(1L, (long) i);
            }
        }
    }

    private Lesson createLesson(Group group) {
        Lesson lesson = new Lesson();
        lesson.setDay(1);
        lesson.setTitle("math");
        List<Group> groups = lesson.getGroups();
        groups.add(group);
        lesson.setGroups(groups);
        return lesson;
    }

    private void fillDb(Integer day, String time) {
        Student student1 = new Student();
        student1.setFirstName("student 1");
        student1.setLastName("surname 1");
        Group group1 = new Group();
        group1.setLessons(new ArrayList<>());

        List<Student> students = new ArrayList<>();
        students.add(student1);
        group1.setGroupNumber("911");
        group1.setStudents(students);
        student1.setGroup(group1);

        Lesson lesson1 = new Lesson();
        group1.setLessons(List.of(lesson1));
        lesson1.setTitle("java");
        lesson1.setTime(time);
        lesson1.setDay(day);
        Professor professor = new Professor();
        professor.setFirstName("doctor");
        lesson1.setProfessor(professor);
        studentRepository.save(student1);
    }
}
