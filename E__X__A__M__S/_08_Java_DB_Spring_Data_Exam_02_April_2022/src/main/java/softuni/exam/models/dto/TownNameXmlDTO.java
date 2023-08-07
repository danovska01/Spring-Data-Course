package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameXmlDTO {

    ////////////    Ползва се като XML DTO при импорта на Apartment, където се съдържа като вкложен елемент
////////////    Ползва се и като JSON DTO при импорта на Customer, където също е вложен елемент


    private String name;

    public TownNameXmlDTO() {
    }


    public String getName() {
        return name;
    }


}
