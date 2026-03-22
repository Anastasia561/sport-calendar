package pl.edu.sportcalendar.stage.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.sportcalendar.stage.dto.StageDto;
import pl.edu.sportcalendar.stage.mapper.StageMapper;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.stage.repository.StageRepository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StageServiceTest {
    @Mock
    private StageRepository stageRepository;
    @Mock
    private StageMapper stageMapper;
    @InjectMocks
    private StageServiceImpl stageService;

    @Test
    void shouldReturnStageById_whenExists() {
        Long id = 1L;
        Stage stage = new Stage();
        stage.setId(id);

        when(stageRepository.findById(id)).thenReturn(Optional.of(stage));

        Stage result = stageService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(stageRepository).findById(id);
    }

    @Test
    void shouldThrowEntityNotFoundException_whenNotFoundById() {
        Long id = 1L;

        when(stageRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> stageService.getById(id));

        assertEquals("Stage not found", ex.getMessage());
        verify(stageRepository).findById(id);
    }

    @Test
    void shouldFindAll_whenExists() {
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();

        StageDto dto1 = new StageDto(1L, "test1");
        StageDto dto2 = new StageDto(2L, "test2");

        when(stageRepository.findAll()).thenReturn(List.of(stage1, stage2));
        when(stageMapper.toDto(stage1)).thenReturn(dto1);
        when(stageMapper.toDto(stage2)).thenReturn(dto2);

        List<StageDto> result = stageService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));

        verify(stageRepository).findAll();
        verify(stageMapper).toDto(stage1);
        verify(stageMapper).toDto(stage2);
    }
}
