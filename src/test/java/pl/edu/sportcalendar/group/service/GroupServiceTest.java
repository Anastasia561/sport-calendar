package pl.edu.sportcalendar.group.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.sportcalendar.group.dto.GroupDto;
import pl.edu.sportcalendar.group.mapper.GroupMapper;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.group.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {
    @Mock
    private GroupRepository groupRepository;
    @Mock
    private GroupMapper groupMapper;
    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    void shouldReturnGroupById_whenExists() {
        Long id = 1L;
        Group group = new Group();
        group.setId(id);

        when(groupRepository.findById(id)).thenReturn(Optional.of(group));

        Group result = groupService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(groupRepository).findById(id);
    }

    @Test
    void shouldThrowEntityNotFoundException_whenNotFoundById() {
        Long id = 1L;

        when(groupRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> groupService.getById(id));

        assertEquals("Group not found", ex.getMessage());
        verify(groupRepository).findById(id);
    }

    @Test
    void shouldFindAll_whenExists() {
        Group group1 = new Group();
        Group group2 = new Group();

        GroupDto dto1 = new GroupDto(1L, "test1");
        GroupDto dto2 = new GroupDto(2L, "test2");

        when(groupRepository.findAll()).thenReturn(List.of(group1, group2));
        when(groupMapper.toDto(group1)).thenReturn(dto1);
        when(groupMapper.toDto(group2)).thenReturn(dto2);

        List<GroupDto> result = groupService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));

        verify(groupRepository).findAll();
        verify(groupMapper).toDto(group1);
        verify(groupMapper).toDto(group2);
    }
}
