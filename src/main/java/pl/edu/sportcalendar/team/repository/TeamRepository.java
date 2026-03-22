package pl.edu.sportcalendar.team.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.sportcalendar.team.model.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
