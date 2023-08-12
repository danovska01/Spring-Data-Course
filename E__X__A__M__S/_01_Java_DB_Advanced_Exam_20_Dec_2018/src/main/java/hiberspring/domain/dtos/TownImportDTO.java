package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class TownImportDTO {

    // "name": "Kairo",
    //    "population": 5432000
    @NotNull
    private String name;
    @Positive
    private int population;

    public TownImportDTO() {

    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }
}
