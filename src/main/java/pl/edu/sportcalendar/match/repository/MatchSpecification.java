package pl.edu.sportcalendar.match.repository;

import org.springframework.data.jpa.domain.Specification;
import pl.edu.sportcalendar.competition.model.Sport;
import pl.edu.sportcalendar.match.model.Match;

import java.time.LocalDate;

public class MatchSpecification {
    public static Specification<Match> build(Sport sport, LocalDate date) {

        return Specification
                .where(hasSport(sport))
                .and(hasDate(date));
    }


    private static Specification<Match> hasSport(Sport sport) {
        return (root, query, cb) ->
                sport == null ? null :
                        cb.equal(root.get("competition").get("sport"), sport);
    }

    private static Specification<Match> hasDate(LocalDate date) {
        return (root, query, cb) ->
                date == null ? null :
                        cb.equal(root.get("date"), date);
    }
}
