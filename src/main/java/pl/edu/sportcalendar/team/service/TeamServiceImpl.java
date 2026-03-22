package pl.edu.sportcalendar.team.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.team.dto.TeamDto;
import pl.edu.sportcalendar.team.mapper.TeamMapper;
import pl.edu.sportcalendar.team.model.Team;
import pl.edu.sportcalendar.team.repository.TeamRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Override
    public Team getById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found"));
    }

    @Override
    public List<TeamDto> getAll() {
        return teamRepository.findAll().stream()
                .map(teamMapper::toDto)
                .toList();
    }
}
