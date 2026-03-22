package pl.edu.sportcalendar.competition.service;

import pl.edu.sportcalendar.competition.dto.CompetitionDto;
import pl.edu.sportcalendar.competition.model.Competition;

import java.util.List;

public interface CompetitionService {
    Competition getById(Long id);

    List<CompetitionDto> getAll();
}
