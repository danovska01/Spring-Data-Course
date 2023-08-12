package hiberspring.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportEmployeesRootDTO {

    @XmlElement(name = "employee")
    private List<ImportEmployeeDTO> employees;

    public ImportEmployeesRootDTO() {

    }

    public List<ImportEmployeeDTO> getEmployees() {
        return employees;
    }
}
