package pl.edu.sportcalendar.team.service;

import pl.edu.sportcalendar.team.dto.TeamDto;
import pl.edu.sportcalendar.team.model.Team;

import java.util.List;

public interface TeamService {
    Team getById(Long id);
    List<TeamDto> getAll();
}
