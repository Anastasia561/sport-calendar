package pl.edu.sportcalendar.match.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.competition.service.CompetitionService;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.group.service.GroupService;
import pl.edu.sportcalendar.match.dto.MatchCreateDto;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.mapper.MatchMapper;
import pl.edu.sportcalendar.match.model.Match;
import pl.edu.sportcalendar.match.repository.MatchRepository;
import pl.edu.sportcalendar.match.repository.MatchSpecification;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stadium.service.StadiumService;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.stage.service.StageService;
import pl.edu.sportcalendar.team.model.Team;
import pl.edu.sportcalendar.team.service.TeamService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;
    private final StadiumService stadiumService;
    private final StageService stageService;
    private final GroupService groupService;
    private final CompetitionService competitionService;
    private final TeamService teamService;

    @Override
    public MatchResponseDto getById(long id) {
        return matchRepository.findById(id)
                .map(matchMapper::toMatchResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));
    }

    @Override
    public Page<MatchResponseDto> getAllPageable(Pageable pageable, Sport sport, LocalDate date) {
        return matchRepository.findAll(MatchSpecification.build(sport, date), pageable)
                .map(matchMapper::toMatchResponseDto);
    }

    @Override
    @Transactional
    public long create(MatchCreateDto dto) {
        if (dto.homeTeamId().equals(dto.awayTeamId())) {
            throw new IllegalArgumentException("Home and away teams should be different");
        }

        Competition competition = competitionService.getById(dto.competitionId());
        Stage stage = stageService.getById(dto.stageId());
        Group group = dto.groupId() != null ? groupService.getById(dto.groupId()) : null;
        Stadium stadium = dto.stadiumId() != null ? stadiumService.getById(dto.stadiumId()) : null;
        Team homeTeam = dto.homeTeamId() != null ? teamService.getById(dto.homeTeamId()) : null;
        Team awayTeam = teamService.getById(dto.awayTeamId());

        Match entity = matchMapper.toEntity(dto, competition, homeTeam, awayTeam, stage, group, stadium);
        return matchRepository.save(entity).getId();
    }
}
