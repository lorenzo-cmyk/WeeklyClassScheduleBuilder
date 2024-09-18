package university.wcsb.iCalendar;

import net.fortuna.ical4j.model.component.VEvent;
import university.wcsb.DTOs.CourseDTO;
import university.wcsb.iCalendar.utilities.VEventFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDTOtoVEventListConverter {
    private final LocalDate startWeekDate;
    private final CourseDTO courseDTO;

    public CourseDTOtoVEventListConverter(LocalDate startWeekDate, CourseDTO courseDTO) {
        if (startWeekDate == null || courseDTO == null) {
            throw new IllegalArgumentException("Trying to create a CourseDTOtoVEventListConverter with invalid data.");
        }
        if (startWeekDate.getDayOfWeek() != DayOfWeek.MONDAY) {
            throw new IllegalArgumentException("Trying to create a CourseDTOtoVEventListConverter with a startWeekDate that is not a Monday.");
        }
        this.startWeekDate = startWeekDate;
        this.courseDTO = courseDTO;
    }

    public List<VEvent> getVEventList() {
        return courseDTO.weeklyLessons().stream()
                .map(lesson -> {
                    VEventFactory vEventFactory = new VEventFactory(
                            courseDTO.name() + " - " + lesson.id(),
                            startWeekDate.plusDays(lesson.dayOfWeek().getValue() - 1),
                            lesson.startTime(),
                            lesson.endTime(),
                            lesson.room()
                    );
                    return vEventFactory.getVEvent();
                })
                .collect(Collectors.toList());
    }

}
