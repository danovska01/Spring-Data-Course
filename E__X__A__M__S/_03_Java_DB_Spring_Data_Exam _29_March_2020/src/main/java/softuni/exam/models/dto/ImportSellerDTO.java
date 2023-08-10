package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportSellerDTO {

    //        <first-name>Wade</first-name>
    //        <last-name>F</last-name>
    //        <email>wforseith1@umich.edu</email>
    //        <rating>GOOD</rating>
    //        <town>Juhut</town>
    @XmlElement(name = "first-name") // Use @XmlElement to map XML elements
    @Size(min = 2, max = 20)
    private String firstName;
    @XmlElement(name = "last-name") // Use @XmlElement to map XML elements
    @Size(min = 2, max = 20)
    private String lastName;
    @Email
    private String email;
    @XmlElement(name = "rating")
    @NotNull
    private RatingDTO rating;
    @XmlElement(name = "town")
    @NotNull
    private String town;

    public ImportSellerDTO() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public RatingDTO getRating() {
        return rating;
    }

    public String getTown() {
        return town;
    }
}
