package pl.edu.sportcalendar.wrapper;

import java.time.OffsetDateTime;
import java.util.List;

public record ValidationError(
        String message,
        OffsetDateTime timestamp,
        List<FieldValidationError> validationErrors) implements ResponseError {
}
