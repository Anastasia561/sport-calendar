package pl.edu.sportcalendar.stadium.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Stadiums", description = "Endpoints for managing stadiums")
public class StadiumController {
    private final StadiumService stadiumService;

    @Operation(summary = "Get list of all stadiums")
    @GetMapping
    public ResponseWrapper<List<StadiumDto>> findAll() {
        return ResponseWrapper.ok(stadiumService.getAll());
    }
}
