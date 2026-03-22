package pl.edu.sportcalendar.group.service;

import pl.edu.sportcalendar.group.dto.GroupDto;
import pl.edu.sportcalendar.group.model.Group;

import java.util.List;

public interface GroupService {
    Group getById(long id);

    List<GroupDto> getAll();
}
