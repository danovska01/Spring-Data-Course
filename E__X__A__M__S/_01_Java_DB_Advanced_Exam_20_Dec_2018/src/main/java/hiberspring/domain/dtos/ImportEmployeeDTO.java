package hiberspring.domain.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeeDTO {

    //  </employee>
    //    <employee first-name="Jefrey" last-name="Hunnington" position="Office Manager">
    //        <card>DXKwE-pprkA-dLT9g-bGnbp-1304U</card>
    //        <branch>Pekin Funny Branch</branch>
    //  </employee>

    @NotNull
    @XmlAttribute(name = "first-name")
    private String firstName;

    @NotNull
    @XmlAttribute(name = "last-name")
    private String lastName;

    @NotNull
    @XmlAttribute(name = "position")
    private String position;
    @NotNull
    @XmlElement
    private String card;
    @NotNull
    @XmlElement
    private String branch;

    public ImportEmployeeDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getCard() {
        return card;
    }

    public String getBranch() {
        return branch;
    }
}
