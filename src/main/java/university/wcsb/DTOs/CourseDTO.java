package university.wcsb.DTOs;

import java.util.List;

public record CourseDTO(String name, List<LessonDTO> weeklyLessons) {
    public CourseDTO {
        if (name == null || name.isBlank() || weeklyLessons == null || weeklyLessons.isEmpty()) {
            throw new IllegalArgumentException("Trying to create a CourseDTO with invalid data.");
        }
    }
}
