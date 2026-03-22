package pl.edu.sportcalendar.team.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.sportcalendar.team.dto.TeamDto;
import pl.edu.sportcalendar.team.mapper.TeamMapper;
import pl.edu.sportcalendar.team.model.Team;
import pl.edu.sportcalendar.team.repository.TeamRepository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private TeamMapper teamMapper;
    @InjectMocks
    private TeamServiceImpl teamService;

    @Test
    void shouldReturnTeamById_whenExists() {
        Long id = 1L;
        Team team = new Team();
        team.setId(id);

        when(teamRepository.findById(id)).thenReturn(Optional.of(team));

        Team result = teamService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(teamRepository).findById(id);
    }

    @Test
    void shouldThrowEntityNotFoundException_whenNotFoundById() {
        Long id = 1L;

        when(teamRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> teamService.getById(id));

        assertEquals("Team not found", ex.getMessage());
        verify(teamRepository).findById(id);
    }

    @Test
    void shouldFindAll_whenExists() {
        Team team1 = new Team();
        Team team2 = new Team();

        TeamDto dto1 = new TeamDto(1L, "team1");
        TeamDto dto2 = new TeamDto(2L, "team2");


        when(teamRepository.findAll()).thenReturn(List.of(team1, team2));
        when(teamMapper.toDto(team1)).thenReturn(dto1);
        when(teamMapper.toDto(team2)).thenReturn(dto2);

        List<TeamDto> result = teamService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));

        verify(teamRepository).findAll();
        verify(teamMapper).toDto(team1);
        verify(teamMapper).toDto(team2);
    }
}
