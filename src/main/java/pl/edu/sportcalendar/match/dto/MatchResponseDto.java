package pl.edu.sportcalendar.match.dto;

import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.match.model.Status;
import pl.edu.sportcalendar.stage.dto.StageResponseDto;
import pl.edu.sportcalendar.team.dto.TeamResponseDto;

import java.time.LocalDate;
import java.time.LocalTime;

public record MatchResponseDto(
        Sport sport,
        Integer season,
        Status status,
        LocalTime timeVenueUTC,
        LocalDate dateVenue,
        String stadium,
        TeamResponseDto homeTeam,
        TeamResponseDto awayTeam,
        Integer homeGoals,
        Integer awayGoals,
        String winner,
        String message,
        StageResponseDto stage,
        String group,
        String originCompetitionId,
        String originCompetitionName
) {
}
