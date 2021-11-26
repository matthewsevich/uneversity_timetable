package com.example.demo.util;

import com.example.demo.model.Group;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;

    @Override
    public void run(ApplicationArguments args) {
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
        List<Group> groups = new ArrayList<>();
        group1.setLessons(Arrays.asList(lesson1));
        groups.add(group1);
        lesson1.setTitle("java");
        studentRepository.save(student1);

//        groupRepository.save(group1);
//        lessonRepository.save(lesson1);

    }
}
