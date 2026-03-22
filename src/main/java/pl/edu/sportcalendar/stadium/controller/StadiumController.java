package pl.edu.sportcalendar.stadium.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.stadium.dto.StadiumDto;
import pl.edu.sportcalendar.stadium.service.StadiumService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/stadiums")
@RequiredArgsConstructor
public class StadiumController {
    private final StadiumService stadiumService;

    @GetMapping
    public ResponseWrapper<List<StadiumDto>> findAll() {
        return ResponseWrapper.ok(stadiumService.getAll());
    }
}
