//package com.example.demo.converter;
//
//import com.example.demo.dto.GroupDto;
//import com.example.demo.model.Group;
//import lombok.RequiredArgsConstructor;
//
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//public class GroupConverter {
//
//    private final StudentConverter studentConverter;
//    private final LessonConverter lessonConverter;
//
//    public GroupDto fillDto(Group enity) {
//        GroupDto dto = new GroupDto();
//        dto.setId(enity.getId());
//        dto.setStudents(enity.getStudents()
//                .stream()
//                .map(studentConverter::fillDto)
//                .collect(Collectors.toSet()));
//        dto.setLessonList(enity.getLessonList()
//                .stream()
//                .map(lessonConverter::fillDto));
//    }
//    }
