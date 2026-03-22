package pl.edu.sportcalendar.competition.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.competition.repository.CompetitionRepository;

@Service
@RequiredArgsConstructor
class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    @Override
    public Competition getById(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found"));
    }
}
