package university.wcsb.core;

import university.wcsb.DTOs.IO.JSONtoCourseDTOConverter;
import university.wcsb.iCalendar.CourseDTOtoCalendar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class ConvertJSONtoICS {
    public static void convertJSONtoICS(Path inputPath, LocalDate startDay, Path outputPath) {
        try {
            String ICS = new CourseDTOtoCalendar(startDay, new JSONtoCourseDTOConverter(Files.readString(inputPath)).getCourseDTO()).getCalendar().toString();
            Files.writeString(outputPath, ICS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
