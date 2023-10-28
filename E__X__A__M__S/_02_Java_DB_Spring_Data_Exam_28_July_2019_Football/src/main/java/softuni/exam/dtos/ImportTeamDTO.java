package softuni.exam.dtos;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTeamDTO {
    //<team>
    //        <name>North Hub</name>
    //        <picture>
    //            <url>fc_pictures_2</url>
    //        </picture>
    // </team>
    @Size(min = 3, max = 20)
    private String name;
    private PictureXMLDTO picture;

    public ImportTeamDTO() {

    }

    public String getName() {
        return name;
    }

    public PictureXMLDTO getPicture() {
        return picture;
    }
}
