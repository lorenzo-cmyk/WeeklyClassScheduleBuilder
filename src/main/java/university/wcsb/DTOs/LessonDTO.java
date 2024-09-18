package university.wcsb.DTOs;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record LessonDTO(int id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime, String room) {
    public LessonDTO {
        if (id <= 0 || dayOfWeek == null || startTime == null || endTime == null || room == null || room.isBlank()) {
            throw new IllegalArgumentException("Trying to create a LessonDTO with invalid data.");
        }
    }
}
