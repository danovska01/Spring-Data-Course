import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CourseDTO;

import java.time.Instant;
import java.util.Date;

public class MainDates {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
//                .setDateFormat("YYYY-MM-dd")
                .setPrettyPrinting()
                .create();

        CourseDTO math = new CourseDTO("Math", 10, Date.from(Instant.now()));
        CourseDTO literature = new CourseDTO("Literature", 8, Date.from(Instant.now()));

        // custom type adapter for LocalDate

        System.out.println(gson.toJson(math));
        System.out.println(gson.toJson(literature));

        String mathJson = """
                        {
                          "name": "Math",
                          "lengthInWeeks": 10,
                          "createdAt": "2023-07-08"
                        }
                """;

        String literatureJson = """
                {
                  "name": "Literature",
                  "lengthInWeeks": 8,
                  "createdAt": "Jul 30, 2023, 10:04:35 AM"
                }
                """;

        CourseDTO courseDTO = gson.fromJson(mathJson, CourseDTO.class);
        CourseDTO course2DTO = gson.fromJson(literatureJson, CourseDTO.class);
        System.out.println(courseDTO);
        System.out.println(course2DTO);
    }
}
