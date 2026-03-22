package pl.edu.sportcalendar.team.dto;

public record TeamResponseDto(
        String name,
        String officialName,
        String slug,
        String abbreviation,
        String teamCountryCode
) {
}
