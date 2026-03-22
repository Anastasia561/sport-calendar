package pl.edu.sportcalendar.stadium.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stadium.repository.StadiumRepository;

@Service
@RequiredArgsConstructor
class StadiumServiceImpl implements StadiumService {
    private final StadiumRepository stadiumRepository;

    @Override
    public Stadium getById(long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stadium not found"));
    }
}
