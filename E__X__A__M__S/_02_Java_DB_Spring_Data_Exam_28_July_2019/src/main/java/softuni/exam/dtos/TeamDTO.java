package softuni.exam.dtos;

import javax.validation.constraints.Size;

public class TeamDTO {
    @Size(min = 3, max = 20)
    private String name;
    private PictureDTO picture;

    public TeamDTO() {

    }

    public String getName() {
        return name;
    }

    public PictureDTO getPicture() {
        return picture;
    }
}
