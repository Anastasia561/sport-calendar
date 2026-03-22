package pl.edu.sportcalendar.wrapper;

import java.time.OffsetDateTime;

public record GeneralError(String message, OffsetDateTime timestamp) implements ResponseError {
}
