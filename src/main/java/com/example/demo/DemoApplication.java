package com.example.demo;

import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void runAfterStartup() {
//        Student student1 = new Student();
//        student1.setFirstName("student 1");
//        student1.setLastName("surname 1");
//        Group group1 = new Group();
//        group1.setLessons(new ArrayList<>());
//
//        List<Student> students = new ArrayList<>();
//        students.add(student1);
//        group1.setGroupNumber("911");
//        group1.setStudents(students);
//        student1.setGroup(group1);
//
//        Lesson lesson1 = new Lesson();
//        List<Group> groups = new ArrayList<>();
//        group1.setLessons(List.of(lesson1));
//        groups.add(group1);
//        lesson1.setTitle("java");
//        lesson1.setTime("10pm");
//        lesson1.setDay(1);
//        Professor professor = new Professor();
//        professor.setFirstName("doctor");
//        lesson1.setProfessor(professor);
//        studentRepository.save(student1);
//    }
}
