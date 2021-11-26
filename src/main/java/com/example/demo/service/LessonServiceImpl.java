package com.example.demo.service;

import com.example.demo.converter.CycleAvoidingMappingContext;
import com.example.demo.converter.LessonMapper;
import com.example.demo.dto.LessonDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Lesson;
import com.example.demo.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonMapper mapper;
    private final LessonRepository repository;
    private final CycleAvoidingMappingContext context;

    @Override
    public List<LessonDto> getAllLessons() {
        log.info("call getAll");
        return repository.findAll()
                .stream()
                .map(l -> mapper.toDto(l, context))
                .collect(Collectors.toList());
    }

    @Override
    public LessonDto findById(Long id) {
        log.info("call find byId");
        Lesson lesson = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no such lesson found"));
        return mapper.toDto(lesson, context);

    }

    @Override
    public LessonDto updateLesson(LessonDto dto, Long id) {
        log.info("call update");
        Lesson lesson = repository.findById(id).orElseThrow(() -> new NoSuchEntityException("no such lesson found"));
        dto.setId(id);
        Lesson save = repository.save(mapper.toEntity(dto, context));
        return mapper.toDto(save, context);
    }

    @Override
    public void deleteLesson(Long id) {
        repository.deleteById(id);
    }

    @Override
    public LessonDto saveLesson(LessonDto dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto, context)), context);
    }
}
