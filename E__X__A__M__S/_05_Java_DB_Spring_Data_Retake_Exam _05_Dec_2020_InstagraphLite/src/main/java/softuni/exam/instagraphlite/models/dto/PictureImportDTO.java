package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PictureImportDTO {
    //  "path": "src/folders/resources/images/post/timeline/png/27kLXVm22Q.png",
    //    "size": 44273.27

    @NotNull
    private String path;

    @DecimalMin("500")
    @DecimalMax("60000")
    private double size;


    public PictureImportDTO() {
    }

    public String getPath() {
        return path;
    }

    public double getSize() {
        return size;
    }
}
