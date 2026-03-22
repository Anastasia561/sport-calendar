package pl.edu.sportcalendar.stadium.service;

import pl.edu.sportcalendar.stadium.dto.StadiumDto;
import pl.edu.sportcalendar.stadium.model.Stadium;

import java.util.List;

public interface StadiumService {
    Stadium getById(long id);

    List<StadiumDto> getAll();
}
