package pl.edu.sportcalendar.match.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record MatchCreateDto(

        @NotNull(message = "Season is required")
        @Min(value = 1900, message = "Season must be valid")
        Long season,

        @NotNull(message = "Date is required")
        @Future(message = "Date must be in the future")
        LocalDate date,

        @NotNull(message = "Time is required")
        LocalTime timeUtc,

        @Size(max = 250, message = "Message must be at most 500 characters")
        String message,

        @Positive(message = "Stadium id must be positive")
        Long stadiumId,
        @Positive(message = "Stage id must be positive")
        Long stageId,
        @Positive(message = "Group id must be positive")
        Long groupId,

        @Positive(message = "Competition id must be positive")
        @NotNull(message = "Competition is required")
        Long competitionId,

        @Positive(message = "Home team id must be positive")
        Long homeTeamId,

        @Positive(message = "Away team id must be positive")
        @NotNull(message = "Away team is required")
        Long awayTeamId
) {
}
