package pl.edu.sportcalendar.group.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import pl.edu.sportcalendar.AbstractControllerIntegrationTest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GroupControllerTest extends AbstractControllerIntegrationTest {
    @Test
    void shouldReturnEmptyList_whenNoExists() throws Exception {
        performRequest(HttpMethod.GET, "/groups", null)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(0));
    }
}
