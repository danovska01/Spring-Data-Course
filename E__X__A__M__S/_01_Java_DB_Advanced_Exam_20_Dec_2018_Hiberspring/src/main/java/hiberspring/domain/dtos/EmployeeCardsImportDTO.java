package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;

public class EmployeeCardsImportDTO {
    //"number": "UAIP0-0UVao-3axBt-vWF8c-45paZ"
    @NotNull
    private String number;

    public EmployeeCardsImportDTO() {

    }

    public String getNumber() {
        return number;
    }
}
