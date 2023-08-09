package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlaneRootDTO {

    //  <plane>
    //        <register-number>1D4RD3GG2BC580775</register-number>
    //        <capacity>-122</capacity>
    //        <airline>Steuber and Sons</airline>
    // </plane>

    @XmlElement(name = "plane")
    private List<ImportPlaneDTO> planes;

    public ImportPlaneRootDTO() {

    }

    public List<ImportPlaneDTO> getPlanes() {
        return planes;
    }
}
