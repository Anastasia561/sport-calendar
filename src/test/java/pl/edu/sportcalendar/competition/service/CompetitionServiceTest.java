package pl.edu.sportcalendar.competition.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.sportcalendar.competition.dto.CompetitionDto;
import pl.edu.sportcalendar.competition.mapper.CompetitionMapper;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.competition.repository.CompetitionRepository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompetitionServiceTest {
    @Mock
    private CompetitionRepository competitionRepository;
    @Mock
    private CompetitionMapper competitionMapper;
    @InjectMocks
    private CompetitionServiceImpl competitionService;

    @Test
    void shouldReturnCompetitionById_whenExists() {
        Long id = 1L;
        Competition competition = new Competition();
        competition.setId(id);

        when(competitionRepository.findById(id)).thenReturn(Optional.of(competition));

        Competition result = competitionService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(competitionRepository).findById(id);
    }

    @Test
    void shouldThrowEntityNotFoundException_whenNotFoundById() {
        Long id = 1L;

        when(competitionRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> competitionService.getById(id));

        assertEquals("Competition not found", ex.getMessage());
        verify(competitionRepository).findById(id);
    }

    @Test
    void shouldFindAll_whenExists() {
        Competition competition1 = new Competition();
        Competition competition2 = new Competition();

        CompetitionDto dto1 = new CompetitionDto(1L, "Test1");
        CompetitionDto dto2 = new CompetitionDto(2L, "Test2");

        when(competitionRepository.findAll()).thenReturn(List.of(competition1, competition2));
        when(competitionMapper.toDto(competition1)).thenReturn(dto1);
        when(competitionMapper.toDto(competition2)).thenReturn(dto2);

        List<CompetitionDto> result = competitionService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));

        verify(competitionRepository).findAll();
        verify(competitionMapper).toDto(competition1);
        verify(competitionMapper).toDto(competition2);
    }
}
