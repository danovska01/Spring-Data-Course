package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class ImportTicketRootDTO {
    // <ticket>
    //        <serial-number>T</serial-number>
    //        <price>8028</price>
    //        <take-off>2020-08-12 17:53:35</take-off>
    //        <from-town>
    //            <name>Los Angels</name>
    //        </from-town>
    //        <to-town>
    //            <name>Sofia</name>
    //        </to-town>
    //        <passenger>
    //            <email>gfraschettil@theglobeandmail.com</email>
    //        </passenger>
    //        <plane>
    //            <register-number>JN1CV6AP3BM793273</register-number>
    //        </plane>
    //  </ticket>

    @XmlElement(name = "ticket")
    private List<ImportTicketDTO> tickets;

    public ImportTicketRootDTO() {

    }

    public List<ImportTicketDTO> getTickets() {
        return tickets;
    }
}
