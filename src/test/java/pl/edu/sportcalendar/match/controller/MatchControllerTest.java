package pl.edu.sportcalendar.match.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.ResultActions;
import pl.edu.sportcalendar.AbstractControllerIntegrationTest;
import pl.edu.sportcalendar.match.dto.MatchCreateDto;
import pl.edu.sportcalendar.match.model.Match;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MatchControllerTest extends AbstractControllerIntegrationTest {

    @Test
    void shouldReturnPageOfEvents_whenInputIsValid() throws Exception {
        performRequest(HttpMethod.GET, "/events?page=0&size=2", null)
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content.length()").value(2))
                .andExpect(jsonPath("$.data.totalElements").value(5))
                .andExpect(jsonPath("$.data.totalPages").value(3))
                .andExpect(jsonPath("$.data.number").value(0))

                .andExpect(jsonPath("$.data.content[0].sport").value("FOOTBALL"))
                .andExpect(jsonPath("$.data.content[0].season").value(2024))
                .andExpect(jsonPath("$.data.content[0].status").value("PLAYED"))
                .andExpect(jsonPath("$.data.content[0].timeVenueUTC").value("00:00:00"))
                .andExpect(jsonPath("$.data.content[0].dateVenue").value("2024-01-03"))

                .andExpect(jsonPath("$.data.content[0].stadium").isEmpty())

                .andExpect(jsonPath("$.data.content[0].homeTeam.name").value("Al Shabab"))
                .andExpect(jsonPath("$.data.content[0].homeTeam.officialName").value("Al Shabab FC"))
                .andExpect(jsonPath("$.data.content[0].homeTeam.slug").value("al-shabab-fc"))
                .andExpect(jsonPath("$.data.content[0].homeTeam.abbreviation").value("SHA"))
                .andExpect(jsonPath("$.data.content[0].homeTeam.teamCountryCode").value("KSA"))

                .andExpect(jsonPath("$.data.content[0].awayTeam.name").value("Nasaf"))
                .andExpect(jsonPath("$.data.content[0].awayTeam.officialName").value("FC Nasaf"))
                .andExpect(jsonPath("$.data.content[0].awayTeam.slug").value("fc-nasaf-qarshi"))
                .andExpect(jsonPath("$.data.content[0].awayTeam.abbreviation").value("NAS"))
                .andExpect(jsonPath("$.data.content[0].awayTeam.teamCountryCode").value("UZB"))

                .andExpect(jsonPath("$.data.content[0].homeGoals").value(1))
                .andExpect(jsonPath("$.data.content[0].awayGoals").value(2))
                .andExpect(jsonPath("$.data.content[0].winner").value("Nasaf"))

                .andExpect(jsonPath("$.data.content[0].message").isEmpty())

                .andExpect(jsonPath("$.data.content[0].stage.id").value("ROUND OF 16"))
                .andExpect(jsonPath("$.data.content[0].stage.name").value("ROUND OF 16"))
                .andExpect(jsonPath("$.data.content[0].stage.ordering").value(4))

                .andExpect(jsonPath("$.data.content[0].group").isEmpty())

                .andExpect(jsonPath("$.data.content[0].originCompetitionId")
                        .value("afc-champions-league"))
                .andExpect(jsonPath("$.data.content[0].originCompetitionName")
                        .value("AFC Champions League"));
    }

    @Test
    void shouldReturn404_whenEventNotFound() throws Exception {
        performRequest(HttpMethod.GET, "/events/{id}", null, 100)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.message").value("Match not found"));
    }

    @Test
    void shouldReturnEventById_whenInputIsValid() throws Exception {
        performRequest(HttpMethod.GET, "/events/{id}", null, 1)
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.data.sport").value("FOOTBALL"))
                .andExpect(jsonPath("$.data.season").value(2024))
                .andExpect(jsonPath("$.data.status").value("PLAYED"))
                .andExpect(jsonPath("$.data.timeVenueUTC").value("00:00:00"))
                .andExpect(jsonPath("$.data.dateVenue").value("2024-01-03"))

                .andExpect(jsonPath("$.data.stadium").isEmpty())

                .andExpect(jsonPath("$.data.homeTeam.name").value("Al Shabab"))
                .andExpect(jsonPath("$.data.homeTeam.officialName").value("Al Shabab FC"))
                .andExpect(jsonPath("$.data.homeTeam.slug").value("al-shabab-fc"))
                .andExpect(jsonPath("$.data.homeTeam.abbreviation").value("SHA"))
                .andExpect(jsonPath("$.data.homeTeam.teamCountryCode").value("KSA"))

                .andExpect(jsonPath("$.data.awayTeam.name").value("Nasaf"))
                .andExpect(jsonPath("$.data.awayTeam.officialName").value("FC Nasaf"))
                .andExpect(jsonPath("$.data.awayTeam.slug").value("fc-nasaf-qarshi"))
                .andExpect(jsonPath("$.data.awayTeam.abbreviation").value("NAS"))
                .andExpect(jsonPath("$.data.awayTeam.teamCountryCode").value("UZB"))

                .andExpect(jsonPath("$.data.homeGoals").value(1))
                .andExpect(jsonPath("$.data.awayGoals").value(2))
                .andExpect(jsonPath("$.data.winner").value("Nasaf"))

                .andExpect(jsonPath("$.data.message").isEmpty())

                .andExpect(jsonPath("$.data.stage.id").value("ROUND OF 16"))
                .andExpect(jsonPath("$.data.stage.name").value("ROUND OF 16"))
                .andExpect(jsonPath("$.data.stage.ordering").value(4))

                .andExpect(jsonPath("$.data.group").isEmpty())

                .andExpect(jsonPath("$.data.originCompetitionId").value("afc-champions-league"))
                .andExpect(jsonPath("$.data.originCompetitionName").value("AFC Champions League"));
    }

    @Test
    void shouldCreateEvent_whenInputIsValid() throws Exception {
        MatchCreateDto dto = new MatchCreateDto(2024L, LocalDate.of(2026, 10, 10),
                LocalTime.of(10, 10), null, 1L, 1L, null,
                1L, 1L, 2L);

        ResultActions resultActions = performRequest(HttpMethod.POST, "/events", dto)
                .andExpect(status().isCreated());

        Long id = ((Number) JsonPath.read(
                resultActions.andReturn().getResponse().getContentAsString(),
                "$.data"
        )).longValue();

        Match result = em.find(Match.class, id);
        assertEquals(2024, result.getSeason());
        assertNotNull(result.getStadium());
    }

    @Test
    void shouldReturn400_whenCreateEventWithValidationErrors() throws Exception {
        MatchCreateDto dto = new MatchCreateDto(null, LocalDate.of(2026, 10, 10),
                LocalTime.of(10, 10), null, -1L, 1L, null,
                1L, -1L, 2L);

        performRequest(HttpMethod.POST, "/events", dto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.message").value("Validation failed"))
                .andExpect(jsonPath("$.error.validationErrors").isArray())
                .andExpect(jsonPath("$.error.validationErrors.length()").value(3));
    }
}
