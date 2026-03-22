package pl.edu.sportcalendar.match.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.model.Match;
import pl.edu.sportcalendar.team.mapper.TeamMapper;

@Mapper(componentModel = "spring", uses = TeamMapper.class)
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
}
