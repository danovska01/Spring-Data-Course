package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PictureDTO {
    //  <picture>
    //            <path>src/folders/resources/images/story/blocked/png/1S2el3wJ3v.png</path>
    //  </picture>

    @NotNull
    private String path;


    public PictureDTO() {
    }

    public String getPath() {
        return path;
    }
}
