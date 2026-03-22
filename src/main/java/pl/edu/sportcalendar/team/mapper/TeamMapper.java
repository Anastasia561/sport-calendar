package pl.edu.sportcalendar.team.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.sportcalendar.team.dto.TeamDto;
import pl.edu.sportcalendar.team.dto.TeamResponseDto;
import pl.edu.sportcalendar.team.model.Team;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(source = "country.code", target = "teamCountryCode")
    TeamResponseDto teamToTeamResponseDto(Team team);

    TeamDto toDto(Team team);
}
