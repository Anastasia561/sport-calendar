package pl.edu.sportcalendar.match.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.match.dto.MatchCreateDto;
import pl.edu.sportcalendar.match.dto.MatchResponseDto;
import pl.edu.sportcalendar.match.service.MatchService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

import java.time.LocalDate;

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
    public ResponseWrapper<Page<MatchResponseDto>> getAll(
            Pageable pageable,
            @RequestParam(required = false) Sport sport,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {
        return ResponseWrapper.ok(matchService.getAllPageable(pageable, sport, date));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseWrapper<Long> create(@Valid @RequestBody MatchCreateDto dto) {
        return ResponseWrapper.withStatus(HttpStatus.CREATED, matchService.create(dto));
    }
}
