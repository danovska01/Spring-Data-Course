import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.StudentAdditionalInfoDTO;
import dtos.StudentDTO;

import java.util.Arrays;

public class MainFromJson {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        String json = "{ \"isGraduated\": false, \"averageGrade\": 5.38 }";
        String notFormatted = """
                {
                    "isGraduated": false,
                    "averageGrade": 5.38
                }
                """;

        StudentAdditionalInfoDTO studentAdditionalInfoDTO =
                gson.fromJson(notFormatted, StudentAdditionalInfoDTO.class);
        StudentAdditionalInfoDTO studentAdditionalInfoDTO1 =
                gson.fromJson(json, StudentAdditionalInfoDTO.class);

        System.out.println(studentAdditionalInfoDTO);
        System.out.println(studentAdditionalInfoDTO1);

        String studentJson = """
                {
                  "firstName": "first",
                  "lastName": null,
                  "age": 22,
                  "additionalInfo": {
                    "isGraduated": false,
                    "averageGrade": 5.38
                  },
                  "courses": [
                    {
                      "name": "Math",
                      "lengthInWeeks": 20
                    },
                    {
                      "name": "Biology",
                      "lengthInWeeks": 15
                    }
                  ]
                }
                """;
        StudentDTO studentDTO = gson.fromJson(studentJson, StudentDTO.class);
        System.out.println(studentDTO);

        String jsonArray = """
                [
                  {
                    "firstName": "first",
                    "lastName": null,
                    "age": 22,
                    "additionalInfo": {
                      "isGraduated": false,
                      "averageGrade": 5.38
                    },
                    "courses": [
                      {
                        "name": "Math",
                        "lengthInWeeks": 20
                      },
                      {
                        "name": "Biology",
                        "lengthInWeeks": 15
                      }
                    ]
                  }
                ]
                """;

        StudentDTO[] studentDTOS = gson.fromJson(jsonArray, StudentDTO[].class);
        System.out.println(Arrays.toString(studentDTOS));
    }
}
