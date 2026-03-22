package pl.edu.sportcalendar.team.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Teams", description = "Endpoints for managing teams")
public class TeamController {
    private final TeamService teamService;

    @Operation(summary = "Get list of all teams")
    @GetMapping
    public ResponseWrapper<List<TeamDto>> getAll() {
        return ResponseWrapper.ok(teamService.getAll());
    }
}
