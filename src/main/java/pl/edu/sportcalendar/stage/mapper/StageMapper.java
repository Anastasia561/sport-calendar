package pl.edu.sportcalendar.stage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.edu.sportcalendar.stage.dto.StageDto;
import pl.edu.sportcalendar.stage.dto.StageResponseDto;
import pl.edu.sportcalendar.stage.model.Stage;

@Mapper(componentModel = "spring")
public interface StageMapper {

    @Mapping(source = "publicId", target = "id")
    StageResponseDto toStageResponseDto(Stage stage);

    StageDto toDto(Stage stage);
}
