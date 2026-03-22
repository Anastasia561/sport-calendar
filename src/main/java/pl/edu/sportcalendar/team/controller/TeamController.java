package pl.edu.sportcalendar.team.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.team.dto.TeamDto;
import pl.edu.sportcalendar.team.service.TeamService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

    @GetMapping
    public ResponseWrapper<List<TeamDto>> getAll() {
        return ResponseWrapper.ok(teamService.getAll());
    }
}
