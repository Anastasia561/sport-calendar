package pl.edu.sportcalendar.stadium.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.stadium.dto.StadiumDto;
import pl.edu.sportcalendar.stadium.mapper.StadiumMapper;
import pl.edu.sportcalendar.stadium.model.Stadium;
import pl.edu.sportcalendar.stadium.repository.StadiumRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class StadiumServiceImpl implements StadiumService {
    private final StadiumRepository stadiumRepository;
    private final StadiumMapper stadiumMapper;

    @Override
    public Stadium getById(long id) {
        return stadiumRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stadium not found"));
    }

    @Override
    public List<StadiumDto> getAll() {
        return stadiumRepository.findAll().stream()
                .map(stadiumMapper::toDto)
                .toList();
    }
}
