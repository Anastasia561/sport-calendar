package pl.edu.sportcalendar.stage.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.edu.sportcalendar.stage.dto.StageDto;
import pl.edu.sportcalendar.stage.dto.StageResponseDto;
import pl.edu.sportcalendar.stage.model.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StageMapperTest {
    private StageMapper stageMapper;

    @BeforeEach
    void setUp() {
        stageMapper = Mappers.getMapper(StageMapper.class);
    }

    @Test
    void shouldMapToDto_whenInputIsValid() {
        Stage stage = new Stage();
        stage.setId(1L);
        stage.setName("name");

        StageDto dto = stageMapper.toDto(stage);
        assertEquals(1L, dto.id());
        assertEquals("name", dto.name());
    }

    @Test
    void shouldMapToResponseDto_whenInputIsValid() {
        Stage stage = new Stage();
        stage.setId(1L);
        stage.setName("name");
        stage.setOrdering(3);
        stage.setPublicId("publicId");

        StageResponseDto dto = stageMapper.toStageResponseDto(stage);
        assertEquals("name", dto.name());
        assertEquals("publicId", dto.id());
        assertEquals(3, dto.ordering());
    }

    @Test
    void shouldReturnNull_whenStageIsNullForDto() {
        assertNull(stageMapper.toDto(null));
    }

    @Test
    void shouldReturnNull_whenStageIsNullForResponseDto() {
        assertNull(stageMapper.toStageResponseDto(null));
    }
}
