package pl.edu.sportcalendar.match.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.match.dto.MatchCreateDto;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.model.Match;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stage.mapper.StageMapper;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.team.mapper.TeamMapper;
import pl.edu.sportcalendar.team.model.Team;

@Mapper(componentModel = "spring", uses = {TeamMapper.class, StageMapper.class})
public interface MatchMapper {

    @Mapping(source = "competition.sport", target = "sport")
    @Mapping(source = "timeUtc", target = "timeVenueUTC")
    @Mapping(source = "date", target = "dateVenue")
    @Mapping(
            target = "stadium",
            expression = "java(match.getStadium() != null ? match.getStadium().getName() : null)"
    )
    @Mapping(
            target = "winner",
            expression = "java(match.getWinnerTeam() != null ? match.getWinnerTeam().getName() : null)"
    )
    @Mapping(
            target = "group",
            expression = "java(match.getGroup() != null ? match.getGroup().getName() : null)"
    )
    @Mapping(source = "competition.publicId", target = "originCompetitionId")
    @Mapping(source = "competition.name", target = "originCompetitionName")
    MatchResponseDto toMatchResponseDto(Match match);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "homeGoals", constant = "0")
    @Mapping(target = "awayGoals", constant = "0")
    @Mapping(target = "status", constant = "SCHEDULED")
    @Mapping(target = "winnerTeam", ignore = true)
    Match toEntity(MatchCreateDto dto, Competition competition, Team homeTeam,
                   Team awayTeam, Stage stage, Group group, Stadium stadium);
}
