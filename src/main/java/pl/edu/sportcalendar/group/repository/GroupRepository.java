package pl.edu.sportcalendar.group.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.sportcalendar.group.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
