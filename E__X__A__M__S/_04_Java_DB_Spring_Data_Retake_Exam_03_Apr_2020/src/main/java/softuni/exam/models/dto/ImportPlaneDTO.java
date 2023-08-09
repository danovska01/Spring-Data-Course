package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPlaneDTO {

    // <register-number>1D4RD3GG2BC580775</register-number>
    // <capacity>-122</capacity>
    // <airline>Steuber and Sons</airline>

    // =========   !!!!!
    @XmlElement(name = "register-number") // Use @XmlElement to map XML elements
    @Size(min = 5)
    @NotNull
    private String registerNumber;
    @Positive
    private int capacity;
    @Size(min = 2)
    private String airline;

    public ImportPlaneDTO() {

    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAirline() {
        return airline;
    }
}
