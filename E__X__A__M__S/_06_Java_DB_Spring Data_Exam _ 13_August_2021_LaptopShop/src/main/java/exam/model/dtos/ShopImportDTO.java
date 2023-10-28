package exam.model.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ShopImportDTO {
    @XmlElement
    @Size(min = 4)
    private String address;
    @XmlElement(name = "employee-count")
    @Min(1)
    @Max(50)
    private int employeeCount;
    @XmlElement
    @Min(20000)
    private double income;
    @XmlElement
    @Size(min = 4)
    private String name;
    @XmlElement(name = "shop-area")
    @Min(150)
    private int shopArea;
    @XmlElement(name = "town")
    private NameDTO town;

    public ShopImportDTO() {

    }

    public String getAddress() {
        return address;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public double getIncome() {
        return income;
    }

    public String getName() {
        return name;
    }

    public int getShopArea() {
        return shopArea;
    }

    public NameDTO getTown() {
        return town;
    }
}
