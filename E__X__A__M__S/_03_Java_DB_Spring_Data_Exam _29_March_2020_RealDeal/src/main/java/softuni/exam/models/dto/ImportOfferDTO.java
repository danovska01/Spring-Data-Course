package softuni.exam.models.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.math.BigDecimal;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportOfferDTO {
    // <offer>
    //    <description>kachvash se i karash populace's irrigating advisories exhausting exceptions headphones abdicating
    //            diagnostic devastated newsagents wrapping's discount's
    //    </description>
    //    <price>222359</price>
    //    <added-on>2019-12-23 17:02:39</added-on>
    //    <has-gold-status>true</has-gold-status>
    //     <car>
    //            <id>70</id>
    //     </car>
    //     <seller>
    //            <id>84</id>
    //     </seller>
    //  </offer>

    @Size(min = 5)
    private String description;
    @Positive
    private BigDecimal price;
    @XmlElement(name = "added-on") // Use @XmlElement to map XML elements
    private String addedOn;
    @XmlElement(name = "has-gold-status")// Use @XmlElement to map XML elements
    private boolean hasGoldStatus;
    private IdDTO car;
    private IdDTO seller;

    public ImportOfferDTO() {

    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public IdDTO getCar() {
        return car;
    }

    public IdDTO getSeller() {
        return seller;
    }
}
