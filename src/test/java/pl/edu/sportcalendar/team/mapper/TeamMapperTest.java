package pl.edu.sportcalendar.team.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.edu.sportcalendar.country.model.Country;
import pl.edu.sportcalendar.team.dto.TeamDto;
import pl.edu.sportcalendar.team.dto.TeamResponseDto;
import pl.edu.sportcalendar.team.model.Team;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TeamMapperTest {
    private TeamMapper teamMapper;

    @BeforeEach
    void setup() {
        teamMapper = Mappers.getMapper(TeamMapper.class);
    }

    @Test
    void shouldMapToDto_whenInputIsValid() {
        Team team = new Team();
        team.setId(1L);
        team.setName("ROUND OF 16");

        TeamDto dto = teamMapper.toDto(team);
        assertEquals(1L, dto.id());
        assertEquals("ROUND OF 16", dto.name());
    }

    @Test
    void shouldMapToResponseDto_whenInputIsValid() {
        Team team = new Team();
        team.setId(1L);
        team.setName("ROUND OF 16");
        team.setOfficialName("Official test");
        team.setSlug("test");
        team.setAbbreviation("abbreviation");
        team.setCountry(new Country());

        TeamResponseDto dto = teamMapper.toTeamResponseDto(team);
        assertEquals("Official test", dto.officialName());
        assertEquals("test", dto.slug());
        assertEquals("ROUND OF 16", dto.name());
    }

    @Test
    void shouldReturnNull_whenTeamIsNullForResponseDto() {
        assertNull(teamMapper.toTeamResponseDto(null));
    }

    @Test
    void shouldReturnNull_whenTeamIsNullForDto() {
        assertNull(teamMapper.toDto(null));
    }
}
