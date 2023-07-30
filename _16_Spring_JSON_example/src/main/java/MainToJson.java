import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CourseDTO;
import dtos.StudentAdditionalInfoDTO;
import dtos.StudentDTO;

import java.time.Instant;
import java.util.*;

public class MainToJson {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        StudentDTO studentDTO = new StudentDTO(
                "first",
                null,
                22,
                new StudentAdditionalInfoDTO(false, 5.38),
                List.of(
                        new CourseDTO("Math", 20, Date.from(Instant.now())),
                        new CourseDTO("Biology", 15, Date.from(Instant.now()))
                )
        );

        String json = gson.toJson(studentDTO);
        System.out.println(json);

        Set<StudentDTO> studentDTOList = new HashSet<>();
        studentDTOList.add(studentDTO);
        String jsonList = gson.toJson(studentDTOList);
        System.out.println(jsonList);

        Map<Object, Object> map = new HashMap<>();
        map.put(1, "f");
        map.put(25.5, 25.5);
        map.put(studentDTO, null);

        String jsonMap = gson.toJson(map);
        System.out.println(jsonMap);

    }
}
