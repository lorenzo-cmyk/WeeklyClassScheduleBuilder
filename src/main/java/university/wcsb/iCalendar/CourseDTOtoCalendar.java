package university.wcsb.iCalendar;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.model.property.immutable.ImmutableCalScale;
import university.wcsb.DTOs.CourseDTO;

import java.time.LocalDate;

public class CourseDTOtoCalendar {
    private final LocalDate startWeekDate;
    private final CourseDTO courseDTO;

    public CourseDTOtoCalendar(LocalDate startWeekDate, CourseDTO courseDTO) {
        if (startWeekDate == null || courseDTO == null) {
            throw new IllegalArgumentException("Trying to create a CourseDTOtoCalendar with invalid data.");
        }
        this.startWeekDate = startWeekDate;
        this.courseDTO = courseDTO;
    }

    public Calendar getCalendar() {
        Calendar calendar = new Calendar();
        calendar.add(new ProdId("WCSB"));

        // This is weird, but it's the way it is.
        Version calendarVersion = new Version();
        calendarVersion.setValue(Version.VALUE_2_0);
        calendar.add(calendarVersion);

        calendar.add(ImmutableCalScale.GREGORIAN);
        new CourseDTOtoVEventListConverter(startWeekDate, courseDTO)
                .getVEventList()
                .forEach(calendar::add);
        return calendar;
    }
}
