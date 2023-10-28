package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneDTO {

    // <plane>
    //          <register-number>JN1CV6AP3BM793273</register-number>
    // </plane>
    @NotNull
    @XmlElement(name = "register-number") // Use @XmlElement to map XML elements
    private String registerNumber;

    public PlaneDTO() {

    }

    public String getRegisterNumber() {
        return registerNumber;
    }
}
