package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentNameXmlDTO {

    private String name;

    public ApartmentNameXmlDTO() {

    }

    public String getName() {
        return name;
    }

}
