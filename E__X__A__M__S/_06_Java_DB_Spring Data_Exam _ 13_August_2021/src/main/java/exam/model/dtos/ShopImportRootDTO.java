package exam.model.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shops")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportRootDTO {

    @XmlElement(name = "shop")
    List<ShopImportDTO> shops;

    public ShopImportRootDTO() {
    }

    public List<ShopImportDTO> getShops() {
        return shops;
    }
}
