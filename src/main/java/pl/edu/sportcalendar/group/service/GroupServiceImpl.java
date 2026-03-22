package pl.edu.sportcalendar.group.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.group.dto.GroupDto;
import pl.edu.sportcalendar.group.mapper.GroupMapper;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.group.repository.GroupRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Override
    public Group getById(long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
    }

    @Override
    public List<GroupDto> getAll() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .toList();
    }
}
