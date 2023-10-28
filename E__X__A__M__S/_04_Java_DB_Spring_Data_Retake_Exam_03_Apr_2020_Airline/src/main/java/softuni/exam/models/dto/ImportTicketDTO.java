package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTicketDTO {
    // <serial-number>T</serial-number>
    // <price>8028</price>
    // <take-off>2020-08-12 17:53:35</take-off>
    // <from-town>
    //            <name>Los Angels</name>
    // </from-town>
    // <to-town>
    //            <name>Sofia</name>
    // </to-town>
    // <passenger>
    //            <email>gfraschettil@theglobeandmail.com</email>
    // </passenger>
    // <plane>
    //            <register-number>JN1CV6AP3BM793273</register-number>
    // </plane>
    @Size(min = 2)
    @XmlElement(name = "serial-number") // Use @XmlElement to map XML elements
    private String serialNumber;
    @Positive
    private double price;
    @NotNull
    @XmlElement(name = "take-off") // Use @XmlElement to map XML elements
    private String takeOff;
    @NotNull
    @XmlElement(name = "from-town") // Use @XmlElement to map XML elements
    private TownDTO fromTown;
    @NotNull
    @XmlElement(name = "to-town") // Use @XmlElement to map XML elements
    private TownDTO toTown;
    @NotNull
    private PassengerDTO passenger;
    @NotNull
    private PlaneDTO plane;

    public ImportTicketDTO() {

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public TownDTO getFromTown() {
        return fromTown;
    }

    public TownDTO getToTown() {
        return toTown;
    }

    public PassengerDTO getPassenger() {
        return passenger;
    }

    public PlaneDTO getPlane() {
        return plane;
    }
}
