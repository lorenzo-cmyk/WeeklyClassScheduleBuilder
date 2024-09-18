package university.wcsb.DTOs.IO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import university.wcsb.DTOs.CourseDTO;
import university.wcsb.DTOs.IO.serializers.CustomLocalTimeSerializer;

import java.time.LocalTime;

public class CourseDTOtoJSONConverter {
    private final String JSON;

    public CourseDTOtoJSONConverter(CourseDTO courseDTO) {
        if (courseDTO == null) {
            throw new IllegalArgumentException("Trying to create a CourseDTOtoJSONConverter with a null CourseDTO.");
        }
        this.JSON = convertCourseDTOtoJSON(courseDTO);
    }

    private String convertCourseDTOtoJSON(CourseDTO courseDTO) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalTime.class, new CustomLocalTimeSerializer());
        objectMapper.registerModule(simpleModule);
        try {
            return objectMapper.writeValueAsString(courseDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getJSON() {
        return JSON;
    }
}
