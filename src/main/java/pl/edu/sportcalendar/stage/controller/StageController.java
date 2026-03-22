package pl.edu.sportcalendar.stage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.sportcalendar.stage.dto.StageDto;
import pl.edu.sportcalendar.stage.service.StageService;
import pl.edu.sportcalendar.wrapper.ResponseWrapper;

import java.util.List;

@RestController
@RequestMapping("/stages")
@RequiredArgsConstructor
public class StageController {
    private final StageService stageService;

    @GetMapping
    public ResponseWrapper<List<StageDto>> getAll() {
        return ResponseWrapper.ok(stageService.getAll());
    }
}
