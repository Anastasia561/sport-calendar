package pl.edu.sportcalendar.stage.service;

import pl.edu.sportcalendar.stage.dto.StageDto;
import pl.edu.sportcalendar.stage.model.Stage;

import java.util.List;

public interface StageService {
    Stage getById(Long id);

    List<StageDto> getAll();
}
