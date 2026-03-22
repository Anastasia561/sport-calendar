package pl.edu.sportcalendar.stage.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import pl.edu.sportcalendar.AbstractControllerIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StageControllerTest extends AbstractControllerIntegrationTest {
    @Test
    void shouldReturnListOfStages_whenExists() throws Exception {
        performRequest(HttpMethod.GET, "/stages", null)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("ROUND OF 16"))
                .andExpect(jsonPath("$.data[1].id").value(2))
                .andExpect(jsonPath("$.data[1].name").value("FINAL"));
    }
}
