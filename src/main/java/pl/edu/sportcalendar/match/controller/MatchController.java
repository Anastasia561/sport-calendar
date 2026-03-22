package pl.edu.sportcalendar.match.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.service.MatchService;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/{id}")
    public MatchResponseDto getMatch(@PathVariable long id) {
        return matchService.getById(id);
    }
}
