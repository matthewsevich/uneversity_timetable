package com.example.demo.service;

import com.example.demo.converter.LessonConverter;
import com.example.demo.dto.LessonDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Lesson;
import com.example.demo.model.Student;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.ProfessorRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository repository;
    private final LessonConverter converter;
    private final GroupRepository groupRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;


    @Override
    public List<LessonDto> getAllLessons() {
        log.info("call getAll");
        return repository.findAll()
                .stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto findById(Long id) {
        log.info("call find byId");
        Lesson lesson = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no such lesson found"));
        return converter.toDto(lesson);
    }

    @Override
    public LessonDto updateLesson(LessonDto dto, Long id) {
        log.info("call update lesson with id - {}", id);
        Lesson lesson = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no such lesson found"));
        dto.setId(lesson.getId());
        return fillEntityAndSave(dto);
    }

    private LessonDto fillEntityAndSave(LessonDto dto) {
        Lesson entity = converter.toEntity(dto);
        entity.setProfessor(professorRepository.findById(dto.getProfessorId()).orElseThrow(() -> new NoSuchEntityException("no such professor found")));
        entity.setGroups(groupRepository.findAllById(dto.getGroupsIds()));
        Lesson save = repository.save(entity);
        return converter.toDto(save);
    }

    @Override
    public void deleteLesson(Long id) {
        log.info("call delete lesson with id {}", id);
        repository.deleteById(id);
    }

    @Override
    public LessonDto saveLesson(LessonDto dto) {
        log.info("call save lesson");

        return fillEntityAndSave(dto);
    }

    @Override
    public List<LessonDto> getStudentsLessons(Long id, Integer day) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("no such student"));
        List<Lesson> lessonList = student.getGroup().getLessons().stream()
                .filter(lesson -> lesson.getDay().equals(day))
                .collect(Collectors.toList());
        return lessonList.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }
}
