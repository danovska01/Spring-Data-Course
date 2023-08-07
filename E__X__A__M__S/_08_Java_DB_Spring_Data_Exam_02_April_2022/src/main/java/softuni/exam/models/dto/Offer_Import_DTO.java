package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlElement;

public class Offer_Import_DTO {
    // <offer>
    //        <price>206934.00</price>
    //        <agent>
    //            <name>Dotti</name>
    //        </agent>
    //        <apartment>
    //            <id>56</id>
    //        </apartment>
    //        <publishedOn>28/12/2005</publishedOn>
    //  </offer>
    @XmlElement
    @Positive
    private double price;

    @XmlElement
    private AgentNameXmlDTO agent;

    @XmlElement
    private ApartmentNameXmlDTO apartment;
    @XmlElement
    private String publishedOn;

    public Offer_Import_DTO() {

    }

    public double getPrice() {
        return price;
    }

    public AgentNameXmlDTO getAgent() {
        return agent;
    }

    public ApartmentNameXmlDTO getApartment() {
        return apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }
}
