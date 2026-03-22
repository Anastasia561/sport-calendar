package pl.edu.sportcalendar.match.service;

import pl.edu.sportcalendar.match.dto.MatchResponseDto;

public interface MatchService {
    MatchResponseDto getById(long id);
}
