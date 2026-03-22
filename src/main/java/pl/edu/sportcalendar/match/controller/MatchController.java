package pl.edu.sportcalendar.match.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.service.MatchService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping("/{id}")
    public ResponseWrapper<MatchResponseDto> getById(@PathVariable long id) {
        return ResponseWrapper.ok(matchService.getById(id));
    }

    @GetMapping
    public ResponseWrapper<Page<MatchResponseDto>> getAll(Pageable pageable) {
        return ResponseWrapper.ok(matchService.getAllPageable(pageable));
    }
}
