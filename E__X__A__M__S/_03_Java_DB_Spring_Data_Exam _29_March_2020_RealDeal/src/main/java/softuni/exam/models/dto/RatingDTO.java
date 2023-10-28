package softuni.exam.models.dto;


import javax.xml.bind.annotation.XmlEnumValue;

public enum RatingDTO {
    @XmlEnumValue("GOOD")
    GOOD,

    @XmlEnumValue("BAD")
    BAD,

    @XmlEnumValue("UNKNOWN")
    UNKNOWN
}
