package university.wcsb.DTOs.utilities;

import university.wcsb.DTOs.CourseDTO;
import university.wcsb.DTOs.LessonDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class ExampleCourseDTOFactory {
    private final CourseDTO exampleCourseDTO;

    public ExampleCourseDTOFactory() {
        this.exampleCourseDTO = createCourseDTO();
    }

    private List<LessonDTO> createWeeklyLessons() {
        LessonDTO firstLesson = new LessonDTO(
                1,
                DayOfWeek.MONDAY,
                LocalTime.of(10, 15),
                LocalTime.of(13, 15),
                "B.3.4"
        );

        LessonDTO secondLesson = new LessonDTO(
                2,
                DayOfWeek.TUESDAY,
                LocalTime.of(14, 15),
                LocalTime.of(17, 15),
                "B.2.4"
        );

        LessonDTO thirdLesson = new LessonDTO(
                3,
                DayOfWeek.THURSDAY,
                LocalTime.of(10, 15),
                LocalTime.of(12, 15),
                "2.1.2"
        );

        return List.of(firstLesson, secondLesson, thirdLesson);
    }

    private CourseDTO createCourseDTO() {
        return new CourseDTO(
                "Fondamenti di Automatica",
                createWeeklyLessons()
        );
    }

    public CourseDTO getExampleCourseDTO() {
        return exampleCourseDTO;
    }
}
