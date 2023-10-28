package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportProductRootDTO {

//</product>
//    <product name="Garbage Bag" clients="102849">
//     <branch>Headquarters</branch>
//</product>

    @XmlElement(name = "product")
    private List<ImportProductDTO> products;

    public ImportProductRootDTO() {

    }

    public List<ImportProductDTO> getProducts() {
        return products;
    }
}
