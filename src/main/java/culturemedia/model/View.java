package culturemedia.model;

import java.time.LocalDateTime;

public record View(String user, LocalDateTime dateTime, Integer age, Video video) {
}
