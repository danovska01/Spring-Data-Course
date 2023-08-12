package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;

public class BranchImportDTO {
    // "name": "Headquarters",
    //    "town": "Sofia"
    @NotNull
    private String name;
    @NotNull
    private String town;

    public BranchImportDTO() {

    }

    public String getName() {
        return name;
    }

    public String getTown() {
        return town;
    }
}
