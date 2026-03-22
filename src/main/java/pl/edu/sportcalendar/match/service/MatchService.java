package pl.edu.sportcalendar.match.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;

public interface MatchService {
    MatchResponseDto getById(long id);

    Page<MatchResponseDto> getAllPageable(Pageable pageable);
}
