package bg.softuni.springjson.services;

import bg.softuni.springjson.dtos.StudentDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl {

    private final Gson gson;

    public StudentServiceImpl(Gson gson) {
        this.gson = gson;
    }

    public void create(String createJson) {
        StudentDTO studentDTO = gson.fromJson(createJson, StudentDTO.class);

        System.out.println("Created -> " + studentDTO);
    }

    public String getAllAsJson() {
        return null;
    }


}
