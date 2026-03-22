package pl.edu.sportcalendar.match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.sportcalendar.match.model.Match;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
