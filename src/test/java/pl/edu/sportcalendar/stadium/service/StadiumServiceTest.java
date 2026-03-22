package pl.edu.sportcalendar.stadium.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.edu.sportcalendar.stadium.dto.StadiumDto;
import pl.edu.sportcalendar.stadium.mapper.StadiumMapper;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stadium.repository.StadiumRepository;

import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StadiumServiceTest {
    @Mock
    private StadiumRepository stadiumRepository;
    @Mock
    private StadiumMapper stadiumMapper;
    @InjectMocks
    private StadiumServiceImpl stadiumService;

    @Test
    void shouldReturnStadiumById_whenExists() {
        Long id = 1L;
        Stadium stadium = new Stadium();
        stadium.setId(id);

        when(stadiumRepository.findById(id)).thenReturn(Optional.of(stadium));

        Stadium result = stadiumService.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(stadiumRepository).findById(id);
    }

    @Test
    void shouldThrowEntityNotFoundException_whenNotFoundById() {
        Long id = 1L;

        when(stadiumRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> stadiumService.getById(id));

        assertEquals("Stadium not found", ex.getMessage());
        verify(stadiumRepository).findById(id);
    }

    @Test
    void shouldFindAll_whenExists() {
        Stadium stadium1 = new Stadium();
        Stadium stadium2 = new Stadium();

        StadiumDto dto1 = new StadiumDto(1L, "test1");
        StadiumDto dto2 = new StadiumDto(2L, "test2");

        when(stadiumRepository.findAll()).thenReturn(List.of(stadium1, stadium2));
        when(stadiumMapper.toDto(stadium1)).thenReturn(dto1);
        when(stadiumMapper.toDto(stadium2)).thenReturn(dto2);

        List<StadiumDto> result = stadiumService.getAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));

        verify(stadiumRepository).findAll();
        verify(stadiumMapper).toDto(stadium1);
        verify(stadiumMapper).toDto(stadium2);
    }
}
