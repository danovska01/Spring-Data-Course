package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {

    // "name": "Paris",
    //    "population": 3258941,
    //    "guide": "Vivamus tortor."

    @Size(min = 2)
    private String name;
    @Positive
    @NotNull
    private int population;
    @NotNull
    private String guide;


    public TownImportDTO() {

    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getGuide() {
        return guide;
    }
}
