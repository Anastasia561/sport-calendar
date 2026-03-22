package pl.edu.sportcalendar.match.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;

import java.time.LocalDate;

public interface MatchService {
    MatchResponseDto getById(long id);

    Page<MatchResponseDto> getAllPageable(Pageable pageable, Sport sport, LocalDate date);
}
