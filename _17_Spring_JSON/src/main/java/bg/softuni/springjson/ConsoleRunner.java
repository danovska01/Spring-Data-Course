package bg.softuni.springjson;

import bg.softuni.springjson.dtos.StudentDTO;
import bg.softuni.springjson.services.CourseServiceImpl;
import bg.softuni.springjson.services.StudentServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final StudentServiceImpl studentService;
    private final CourseServiceImpl courseService;

    public ConsoleRunner(StudentServiceImpl studentService, CourseServiceImpl courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) throws Exception {
        StudentDTO studentDTO = new StudentDTO(
                "First",
                20,
                false,
                List.of("Math", "Biology")
        );

        String studentJson = """
                {
                  "firstName": "first",
                  "age": 22,
                  "isGraduated": false,
                  "coursesTaken": [
                    "Math", "Biology"
                  ]
                }
                """;

        studentService.create(studentJson);

        String courseJson = """
                {
                    "name": "Math",
                    "lengthInWeeks": 12
                }
                """;

        courseService.create(courseJson);

    }
}

