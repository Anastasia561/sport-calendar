package pl.edu.sportcalendar.match.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.mapper.MatchMapper;
import pl.edu.sportcalendar.match.repository.MatchRepository;

@Service
@RequiredArgsConstructor
class MatchServiceImpl implements MatchService {
    private final MatchRepository matchRepository;
    private final MatchMapper matchMapper;

    @Override
    public MatchResponseDto getById(long id) {
        return matchRepository.findById(id)
                .map(matchMapper::toMatchResponseDto)
                .orElseThrow(() -> new EntityNotFoundException("Match not found"));
    }
}
