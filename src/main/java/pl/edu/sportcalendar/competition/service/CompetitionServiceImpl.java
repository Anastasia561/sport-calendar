package pl.edu.sportcalendar.competition.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.competition.dto.CompetitionDto;
import pl.edu.sportcalendar.competition.mapper.CompetitionMapper;
import pl.edu.sportcalendar.competition.model.Competition;
import pl.edu.sportcalendar.competition.repository.CompetitionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    @Override
    public Competition getById(Long id) {
        return competitionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Competition not found"));
    }

    @Override
    public List<CompetitionDto> getAll() {
        return competitionRepository.findAll().stream()
                .map(competitionMapper::toDto)
                .toList();
    }
}
