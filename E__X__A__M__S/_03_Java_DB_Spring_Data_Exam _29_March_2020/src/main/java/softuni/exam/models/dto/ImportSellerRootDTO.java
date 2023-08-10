package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSellerRootDTO {
    //  <seller>
    //        <first-name>Wade</first-name>
    //        <last-name>F</last-name>
    //        <email>wforseith1@umich.edu</email>
    //        <rating>GOOD</rating>
    //        <town>Juhut</town>
    //  </seller>

    @XmlElement(name = "seller")
    private List<ImportSellerDTO> sellers;

    public ImportSellerRootDTO() {

    }

    public List<ImportSellerDTO> getSellers() {
        return sellers;
    }
}
