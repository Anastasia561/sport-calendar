package pl.edu.sportcalendar.competition.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.edu.sportcalendar.competition.dto.CompetitionDto;
import pl.edu.sportcalendar.competition.model.Competition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CompetitionMapperTest {
    private CompetitionMapper competitionMapper;

    @BeforeEach
    public void setup() {
        competitionMapper = Mappers.getMapper(CompetitionMapper.class);
    }

    @Test
    void shouldMapToDto_whenInputIsValid() {
        Competition competition = new Competition();
        competition.setId(1L);
        competition.setName("Competition 1");

        CompetitionDto dto = competitionMapper.toDto(competition);
        assertEquals(1L, dto.id());
        assertEquals("Competition 1", dto.name());
    }

    @Test
    void shouldReturnNull_whenCompetitionIsNull() {
        assertNull(competitionMapper.toDto(null));
    }
}
