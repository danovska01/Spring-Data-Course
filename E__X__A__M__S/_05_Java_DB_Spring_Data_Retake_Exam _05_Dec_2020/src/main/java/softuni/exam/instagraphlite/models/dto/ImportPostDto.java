package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPostDto {

    // <post>
    //        <caption>#everything #ring #faith #insta #infinity #swag #sunglasses #smiley #justdoit #the #sleepless #ocean</caption>
    //        <user>
    //            <username>ScoreAntigarein</username>
    //        </user>
    //        <picture>
    //            <path>src/folders/resources/images/story/blocked/png/1S2el3wJ3v.png</path>
    //        </picture>
    //</post>

    @Size(min = 21)
    @NotNull
    private String caption;

    @NotNull
    private PictureDTO picture;

    @NotNull
    private UserDTO user;

    public ImportPostDto() {
    }

    public String getCaption() {
        return caption;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public UserDTO getUser() {
        return user;
    }
}
