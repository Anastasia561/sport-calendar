package pl.edu.sportcalendar.stage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.sportcalendar.stage.model.Stage;

public interface StageRepository extends JpaRepository<Stage, Long> {
}
