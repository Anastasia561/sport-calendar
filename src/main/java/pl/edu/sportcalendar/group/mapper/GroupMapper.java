package pl.edu.sportcalendar.group.mapper;

import org.mapstruct.Mapper;
import pl.edu.sportcalendar.group.dto.GroupDto;
import pl.edu.sportcalendar.group.model.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    GroupDto toDto(Group group);
}
