package pl.edu.sportcalendar.stage.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.stage.dto.StageDto;
import pl.edu.sportcalendar.stage.mapper.StageMapper;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.stage.repository.StageRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class StageServiceImpl implements StageService {
    private final StageRepository stageRepository;
    private final StageMapper stageMapper;

    @Override
    public Stage getById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));
    }

    @Override
    public List<StageDto> getAll() {
        return stageRepository.findAll().stream()
                .map(stageMapper::toDto)
                .toList();
    }
}
