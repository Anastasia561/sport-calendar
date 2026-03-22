package pl.edu.sportcalendar.match.service;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.competition.service.CompetitionService;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.group.service.GroupService;
import pl.edu.sportcalendar.match.dto.MatchCreateDto;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.mapper.MatchMapper;
import pl.edu.sportcalendar.match.model.Match;
import pl.edu.sportcalendar.match.model.Status;
import pl.edu.sportcalendar.match.repository.MatchRepository;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stadium.service.StadiumService;
import pl.edu.sportcalendar.stage.dto.StageResponseDto;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.stage.service.StageService;
import pl.edu.sportcalendar.team.dto.TeamResponseDto;
import pl.edu.sportcalendar.team.model.Team;
import pl.edu.sportcalendar.team.service.TeamService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private MatchMapper matchMapper;
    @Mock
    private StadiumService stadiumService;
    @Mock
    private StageService stageService;
    @Mock
    private GroupService groupService;
    @Mock
    private CompetitionService competitionService;
    @Mock
    private TeamService teamService;

    @InjectMocks
    private MatchServiceImpl matchService;

    @Test
    void shouldGetById_whenMatchExists() {
        long id = 1L;
        Match match = new Match();
        TeamResponseDto team = new TeamResponseDto("ABC", "name official",
                "slug", "abbrev", "AAA");
        StageResponseDto stage = new StageResponseDto("ABC", "name official", 4);

        MatchResponseDto dto = new MatchResponseDto(Sport.FOOTBALL, 2024, Status.SCHEDULED,
                LocalTime.of(10, 0), LocalDate.of(2026, 10, 20),
                "Test stadium", team, null, 0, 0, null,
                "message", stage, null, "test1", "test2");

        when(matchRepository.findById(id)).thenReturn(Optional.of(match));
        when(matchMapper.toMatchResponseDto(match)).thenReturn(dto);

        MatchResponseDto result = matchService.getById(id);

        assertEquals(dto, result);
        verify(matchRepository).findById(id);
        verify(matchMapper).toMatchResponseDto(match);
    }

    @Test
    void shouldThrowEntityNotFoundException_whenNotFoundById() {
        long id = 1L;
        when(matchRepository.findById(id)).thenReturn(Optional.empty());

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class,
                () -> matchService.getById(id));
        assertEquals("Match not found", ex.getMessage());

        verify(matchRepository).findById(id);
        verifyNoInteractions(matchMapper);
    }

    @Test
    void shouldReturnPageOfMatches_whenExist() {
        Pageable pageable = PageRequest.of(0, 10);
        Match match = new Match();
        TeamResponseDto team = new TeamResponseDto("ABC", "name official",
                "slug", "abbrev", "AAA");
        StageResponseDto stage = new StageResponseDto("ABC", "name official", 4);

        MatchResponseDto dto = new MatchResponseDto(Sport.FOOTBALL, 2024, Status.SCHEDULED,
                LocalTime.of(10, 0), LocalDate.of(2026, 10, 20),
                "Test stadium", team, null, 0, 0, null,
                "message", stage, null, "test1", "test2");

        Page<Match> page = new PageImpl<>(List.of(match));

        when(matchRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(matchMapper.toMatchResponseDto(match)).thenReturn(dto);

        Page<MatchResponseDto> result = matchService.getAllPageable(pageable, null, null);

        assertEquals(1, result.getTotalElements());
        assertEquals(dto, result.getContent().getFirst());

        verify(matchRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTeamsAreEqual() {
        MatchCreateDto dto = mock(MatchCreateDto.class);

        when(dto.homeTeamId()).thenReturn(1L);
        when(dto.awayTeamId()).thenReturn(1L);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> matchService.create(dto));

        assertEquals("Home and away teams should be different", ex.getMessage());
        verifyNoInteractions(competitionService, teamService, matchRepository);
    }

    @Test
    void shouldCreateMatch_whenInputIsValid() {
        MatchCreateDto dto = mock(MatchCreateDto.class);

        when(dto.homeTeamId()).thenReturn(1L);
        when(dto.awayTeamId()).thenReturn(2L);
        when(dto.competitionId()).thenReturn(10L);
        when(dto.stageId()).thenReturn(20L);
        when(dto.groupId()).thenReturn(30L);
        when(dto.stadiumId()).thenReturn(40L);

        Competition competition = new Competition();
        Stage stage = new Stage();
        Group group = new Group();
        Stadium stadium = new Stadium();
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        when(competitionService.getById(10L)).thenReturn(competition);
        when(stageService.getById(20L)).thenReturn(stage);
        when(groupService.getById(30L)).thenReturn(group);
        when(stadiumService.getById(40L)).thenReturn(stadium);
        when(teamService.getById(1L)).thenReturn(homeTeam);
        when(teamService.getById(2L)).thenReturn(awayTeam);

        Match match = new Match();
        match.setId(100L);

        when(matchMapper.toEntity(dto, competition, homeTeam, awayTeam, stage, group, stadium)).thenReturn(match);
        when(matchRepository.save(match)).thenReturn(match);

        long result = matchService.create(dto);

        assertEquals(100L, result);
        verify(matchRepository).save(match);
    }
}
