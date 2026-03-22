package pl.edu.sportcalendar.match.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.match.dto.MatchCreateDto;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.model.Match;
import pl.edu.sportcalendar.match.model.Status;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stage.mapper.StageMapper;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.team.mapper.TeamMapper;
import pl.edu.sportcalendar.team.model.Team;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class MatchMapperTest {
    private MatchMapper matchMapper;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        matchMapper = Mappers.getMapper(MatchMapper.class);
        TeamMapper teamMapper = Mappers.getMapper(TeamMapper.class);
        StageMapper stageMapper = Mappers.getMapper(StageMapper.class);

        Field teamMapperField = matchMapper.getClass().getDeclaredField("teamMapper");
        teamMapperField.setAccessible(true);
        teamMapperField.set(matchMapper, teamMapper);

        Field stageMapperField = matchMapper.getClass().getDeclaredField("stageMapper");
        stageMapperField.setAccessible(true);
        stageMapperField.set(matchMapper, stageMapper);
    }

    @Test
    void shouldMapToMatchResponseDto_whenInputIsValid() {
        Competition competition = new Competition();
        competition.setSport(Sport.FOOTBALL);
        competition.setPublicId("comp-123");
        competition.setName("Premier League");

        Stadium stadium = new Stadium();
        stadium.setName("Old Trafford");

        Team winner = new Team();
        winner.setName("Team A");

        Group group = new Group();
        group.setName("Group A");

        Match match = new Match();
        match.setCompetition(competition);
        match.setTimeUtc(LocalTime.of(18, 30));
        match.setDate(LocalDate.of(2025, 5, 10));
        match.setStadium(stadium);
        match.setWinnerTeam(winner);
        match.setGroup(group);

        MatchResponseDto dto = matchMapper.toMatchResponseDto(match);

        assertEquals(Sport.FOOTBALL, dto.sport());
        assertEquals(LocalTime.of(18, 30), dto.timeVenueUTC());
        assertEquals(LocalDate.of(2025, 5, 10), dto.dateVenue());
        assertEquals("Old Trafford", dto.stadium());
        assertEquals("Team A", dto.winner());
        assertEquals("Group A", dto.group());
        assertEquals("comp-123", dto.originCompetitionId());
        assertEquals("Premier League", dto.originCompetitionName());
    }

    @Test
    void shouldMapToEntity_whenInputIsValid() {
        MatchCreateDto dto = new MatchCreateDto(2024L, LocalDate.of(2026, 10, 10),
                LocalTime.of(10, 10), "test", 1L, 1L, 1L,
                1L, 1L, 1L);

        Competition competition = new Competition();
        Team homeTeam = new Team();
        Team awayTeam = new Team();
        Stage stage = new Stage();
        Group group = new Group();
        Stadium stadium = new Stadium();

        Match match = matchMapper.toEntity(dto, competition, homeTeam, awayTeam, stage, group, stadium);

        assertNotNull(match);
        assertNull(match.getId());
        assertEquals(0, match.getHomeGoals());
        assertEquals(0, match.getAwayGoals());
        assertEquals(Status.SCHEDULED, match.getStatus());
        assertNull(match.getWinnerTeam());
        assertEquals(competition, match.getCompetition());
        assertEquals(homeTeam, match.getHomeTeam());
        assertEquals(awayTeam, match.getAwayTeam());
        assertEquals(stage, match.getStage());
        assertEquals(group, match.getGroup());
        assertEquals(stadium, match.getStadium());
    }

    @Test
    void shouldMapToEntity_whenFieldAreNull() {
        MatchCreateDto dto = new MatchCreateDto(2024L, LocalDate.of(2026, 10, 10),
                LocalTime.of(10, 10), "test", 1L, 1L, 1L,
                1L, 1L, 1L);

        Competition competition = new Competition();
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        Match match = matchMapper.toEntity(dto, competition, homeTeam, awayTeam,
                null, null, null);

        assertNull(match.getStage());
        assertNull(match.getGroup());
        assertNull(match.getStadium());
    }
}
