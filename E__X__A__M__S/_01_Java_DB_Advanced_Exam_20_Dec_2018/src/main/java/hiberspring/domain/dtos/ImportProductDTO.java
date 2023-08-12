package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class ImportProductDTO {

    ////</product>
    ////    <product name="Garbage Bag" clients="102849">
    ////    <branch>Headquarters</branch>
    ////</product>

    @XmlAttribute
    @NotNull
    private String name;
    @XmlAttribute
    @NotNull
    @Positive
    private int clients;

    @XmlElement
    @NotNull
    private String branch;

    public ImportProductDTO() {
    }

    public String getName() {
        return name;
    }

    public int getClients() {
        return clients;
    }

    public String getBranch() {
        return branch;
    }


}
