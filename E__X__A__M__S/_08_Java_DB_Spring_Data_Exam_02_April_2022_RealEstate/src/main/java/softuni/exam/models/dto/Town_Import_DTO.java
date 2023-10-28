package softuni.exam.models.dto;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Town_Import_DTO {

    // "townName": "Matingain",
    //    "population": 2162142

    @SerializedName("townName") // Add this annotation to map the JSON field
    @Size(min = 2)
    @NotBlank
    private String name;


    @Positive
    private int population;

    public Town_Import_DTO() {

    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
