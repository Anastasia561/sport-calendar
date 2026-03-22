package pl.edu.sportcalendar.stadium.mapper;

import org.mapstruct.Mapper;
import pl.edu.sportcalendar.stadium.dto.StadiumDto;
import pl.edu.sportcalendar.stadium.model.Stadium;

@Mapper(componentModel = "spring")
public interface StadiumMapper {
    StadiumDto toDto(Stadium stadium);
}
