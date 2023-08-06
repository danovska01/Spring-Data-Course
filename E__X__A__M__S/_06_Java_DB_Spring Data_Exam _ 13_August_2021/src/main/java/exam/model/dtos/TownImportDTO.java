package exam.model.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownImportDTO {
    @XmlElement
    private String name;
    @XmlElement
    private int population;

    @XmlElement(name = "travel-guide")
    private String travelGuide;

    public TownImportDTO() {

    }


    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getTravelGuide() {
        return travelGuide;
    }

    public boolean isValid() {
        if (this.name.length() < 2) {
            return false;
        }

        if (this.population < 1) {
            return false;
        }

        return this.travelGuide.length() >= 10;
    }
}
