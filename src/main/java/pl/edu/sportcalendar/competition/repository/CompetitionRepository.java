package pl.edu.sportcalendar.competition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.sportcalendar.competition.model.Competition;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}
