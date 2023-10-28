package softuni.exam.models.dto;

import javax.validation.constraints.Size;

public class PictureImportDTO {
    @Size(min = 2, max = 20)
    private String name;
    private String dateAndTime;
    private Long car;

    public PictureImportDTO() {

    }

    public String getName() {
        return name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public Long getCar() {
        return car;
    }
}
