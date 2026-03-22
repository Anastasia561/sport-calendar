package pl.edu.sportcalendar.stage.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.stage.model.Stage;
import pl.edu.sportcalendar.stage.repository.StageRepository;

@Service
@RequiredArgsConstructor
class StageServiceImpl implements StageService {
    private final StageRepository stageRepository;

    @Override
    public Stage getById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stage not found"));
    }
}
