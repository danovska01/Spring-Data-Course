package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownDTO {
    //      <from-town>
    //                <name>Los Angels</name>
    //     </from-town>

    @NotNull
    private String name;

    public TownDTO() {

    }

    public String getName() {
        return name;
    }
}
