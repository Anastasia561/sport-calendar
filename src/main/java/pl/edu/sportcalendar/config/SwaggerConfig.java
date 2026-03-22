package pl.edu.sportcalendar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.edu.sportcalendar.config.properties.OpenApiProperties;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    private final OpenApiProperties openApiProperties;

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(new Info()
                        .title("Sport Calendar API Documentation")
                        .version("1.0")
                        .description("Provides REST endpoints for sport events"))
                .servers(List.of(new Server().url(openApiProperties.getResourceServerUrl())
                        .description(openApiProperties.getServerDescription())));
    }
}
