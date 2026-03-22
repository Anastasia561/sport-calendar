package pl.edu.sportcalendar.group.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.sportcalendar.group.model.Group;
import pl.edu.sportcalendar.group.repository.GroupRepository;

@Service
@RequiredArgsConstructor
class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public Group getById(long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Group not found"));
    }
}
