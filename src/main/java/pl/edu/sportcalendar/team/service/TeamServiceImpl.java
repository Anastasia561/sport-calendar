package pl.edu.sportcalendar.team.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.team.model.Team;
import pl.edu.sportcalendar.team.repository.TeamRepository;

@Service
@RequiredArgsConstructor
class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }
}
