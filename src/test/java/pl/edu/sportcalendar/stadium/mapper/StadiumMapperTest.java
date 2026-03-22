package pl.edu.sportcalendar.stadium.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.edu.sportcalendar.stadium.dto.StadiumDto;
import pl.edu.sportcalendar.stadium.model.Stadium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StadiumMapperTest {
    private StadiumMapper stadiumMapper;

    @BeforeEach
    void setUp() {
        stadiumMapper = Mappers.getMapper(StadiumMapper.class);
    }

    @Test
    void shouldMapToDto_whenInputIsValid() {
        Stadium stadium = new Stadium();
        stadium.setId(1L);
        stadium.setName("Stadium 1");

        StadiumDto dto = stadiumMapper.toDto(stadium);
        assertEquals(1L, dto.id());
        assertEquals("Stadium 1", dto.name());
    }

    @Test
    void shouldReturnNull_whenStadiumIsNull() {
        assertNull(stadiumMapper.toDto(null));
    }
}
