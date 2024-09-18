package university.wcsb.DTOs.IO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import university.wcsb.DTOs.CourseDTO;
import university.wcsb.DTOs.IO.serializers.CustomLocalTimeDeserializer;

import java.time.LocalTime;

public class JSONtoCourseDTOConverter {
    private final CourseDTO courseDTO;

    public JSONtoCourseDTOConverter(String JSON) {
        if (JSON == null || JSON.isBlank()) {
            throw new IllegalArgumentException("Trying to create a JSONtoCourseDTOConverter with a null JSON.");
        }
        this.courseDTO = convertJSONtoCourseDTO(JSON);
    }

    private CourseDTO convertJSONtoCourseDTO(String JSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(LocalTime.class, new CustomLocalTimeDeserializer());
        objectMapper.registerModule(simpleModule);
        try {
            return objectMapper.readValue(JSON, CourseDTO.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }
}
