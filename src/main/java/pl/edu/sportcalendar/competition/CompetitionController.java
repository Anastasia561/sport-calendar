package pl.edu.sportcalendar.competition;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.competition.dto.CompetitionDto;
import pl.edu.sportcalendar.competition.service.CompetitionService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/competitions")
@RequiredArgsConstructor
public class CompetitionController {
    private final CompetitionService competitionService;

    @GetMapping
    public ResponseWrapper<List<CompetitionDto>> getAll() {
        return ResponseWrapper.ok(competitionService.getAll());
    }
}
