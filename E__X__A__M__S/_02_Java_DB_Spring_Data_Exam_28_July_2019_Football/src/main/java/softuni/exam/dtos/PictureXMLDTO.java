package softuni.exam.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class PictureXMLDTO {

    private String url;

    public PictureXMLDTO() {

    }

    public String getUrl() {
        return url;
    }
}
