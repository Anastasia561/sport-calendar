package pl.edu.sportcalendar.competition.mapper;

import org.mapstruct.Mapper;
import pl.edu.sportcalendar.competition.dto.CompetitionDto;
import pl.edu.sportcalendar.competition.model.Competition;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    CompetitionDto toDto(Competition competition);
}
