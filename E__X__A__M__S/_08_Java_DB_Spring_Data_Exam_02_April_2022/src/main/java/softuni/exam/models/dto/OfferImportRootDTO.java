package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "offers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportRootDTO {

    @XmlElement(name = "offer")
    List<Offer_Import_DTO> offers;

    public OfferImportRootDTO() {

    }

    public List<Offer_Import_DTO> getOffers() {
        return offers;
    }
}
