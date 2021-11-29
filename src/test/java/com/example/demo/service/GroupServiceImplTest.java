package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.converter.GroupConverter;
import com.example.demo.dto.GroupDto;
import com.example.demo.exception.NoSuchEntityException;
import com.example.demo.model.Group;
import com.example.demo.repository.GroupRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@TestPropertySource(locations = "classpath:ApplicationTest.properties")
@Transactional
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class GroupServiceImplTest {

    @Resource
    GroupService service;
    @Resource
    GroupRepository repository;
    @Resource
    GroupConverter converter;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void exceptionTest() {
        if (repository.findAll().size() == 0L) {
            assertThrows(NoSuchEntityException.class, () -> service.getGroupById(1L));
        } else fail();
    }

    @Test
    void getGroupById() {
        GroupDto dto = createGroupDto();
        service.saveGroup(dto);
        Group saved = repository.findAll().iterator().next();

        GroupDto groupById = service.getGroupById(saved.getId());
        assertEquals(dto.getGroupNumber(), groupById.getGroupNumber());
        assertEquals(saved.getGroupNumber(), groupById.getGroupNumber());
    }

    @Test
    void getAll() {
        GroupDto dto = createGroupDto();
        repository.save(converter.toEntity(dto));
        dto.setGroupNumber("44444");
        repository.save(converter.toEntity(dto));
        assertEquals(2L, service.getAll().size());

    }

    @Test
    void saveGroup() {
        GroupDto dto = createGroupDto();
        service.saveGroup(dto);
        Group next = repository.findAll().iterator().next();
        assertEquals(next.getGroupNumber(), dto.getGroupNumber());
//        Mockito.verify(repository, Mockito.times(1))
//                .save(converter.toEntity(dto));
    }

    @Test
    void update() {
        GroupDto dto = createGroupDto();
        repository.save(converter.toEntity(dto));
        assertEquals(repository.findAll().size(), 1L);
        Group next = repository.findAll().iterator().next();
        assertEquals(next.getGroupNumber(), dto.getGroupNumber());
        dto.setGroupNumber("1337");
        service.update(dto, next.getId());
        Group updated = repository.findAll().iterator().next();
        assertEquals(dto.getGroupNumber(), updated.getGroupNumber());
    }

    @Test
    void deleteGroup() {
        GroupDto dto = createGroupDto();
        assertEquals(0L, repository.findAll().size());
        repository.save(converter.toEntity(dto));
        assertEquals(repository.findAll().size(), 1L);
        Long id = repository.findAll().iterator().next().getId();
        service.deleteGroup(id);
        assertEquals(repository.findAll().size(), 0);
    }

    private GroupDto createGroupDto() {
        GroupDto dto = new GroupDto();
        dto.setGroupNumber("91111");
        dto.setLessonsIds(List.of(1L, 2L, 3L));
        dto.setStudentsIds(List.of(1L, 2L, 3L));
        return dto;
    }
}