package softuni.exam.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "teams")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTeamRootDTO {

    @XmlElement(name = "team")
    private List<ImportTeamDTO> teams;

    public ImportTeamRootDTO() {

    }

    public List<ImportTeamDTO> getTeams() {
        return teams;
    }
}
