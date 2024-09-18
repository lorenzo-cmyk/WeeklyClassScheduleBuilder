package university.wcsb.core;

import university.wcsb.DTOs.IO.CourseDTOtoJSONConverter;
import university.wcsb.DTOs.utilities.ExampleCourseDTOFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GenerateExampleJSONFile {
    public static void generateExampleJSONFile(Path outputPath) {
        try {
            String JSON = new CourseDTOtoJSONConverter(new ExampleCourseDTOFactory().getExampleCourseDTO()).getJSON();
            Files.writeString(outputPath, JSON);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
