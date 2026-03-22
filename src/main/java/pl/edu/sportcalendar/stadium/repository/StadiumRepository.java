package pl.edu.sportcalendar.stadium.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.sportcalendar.stadium.model.Stadium;

public interface StadiumRepository extends JpaRepository<Stadium, Long> {
}
