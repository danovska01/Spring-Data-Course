package softuni.exam.dtos;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportPictureDTO {

    //<url>google.pictures#2</url>
    @NotNull
    private String url;

    public ImportPictureDTO() {

    }

    public String getUrl() {
        return url;
    }
}
