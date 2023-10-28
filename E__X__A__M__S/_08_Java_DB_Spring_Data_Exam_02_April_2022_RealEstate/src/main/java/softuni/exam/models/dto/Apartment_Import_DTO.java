package softuni.exam.models.dto;


import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Apartment_Import_DTO {
    // <apartment>
    //        <apartmentType>three_rooms</apartmentType>
    //        <area>53.47</area>
    //        <town>Lille</town>
    //    </apartment>


    @XmlElement(name = "apartmentType")
    private ApartmentTypeXmlDTO apartmentType;

    @Min(40)
    @XmlElement(name = "area")
    private double area;

    @XmlElement(name = "town")
    private String town;

    public Apartment_Import_DTO() {

    }

    public ApartmentTypeXmlDTO getApartmentType() {
        return apartmentType;
    }

    public double getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
}
