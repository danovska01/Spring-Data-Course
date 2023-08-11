package softuni.exam.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "pictures")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPictureRootDTO {

    // <picture>
    //        <url>google.pictures#2</url>
    //  </picture>

    @XmlElement(name = "picture")
    private List<ImportPictureDTO> pictures;

    public ImportPictureRootDTO() {

    }

    public List<ImportPictureDTO> getPictures() {
        return pictures;
    }
}
