package softuni.exam.dtos;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class PlayerImportDTO {
    //  {
    //    "firstName": "Kiril",
    //    "lastName": "Despodov",
    //    "number": 32,
    //    "salary": 150000.00,
    //    "position": "Invalid",
    //    "picture": {
    //      "url": "google.pictures#1"
    //    },
    //    "team": {
    //      "name": "West Valley",
    //      "picture": {
    //        "url": "fc_pictures_1"
    //      }
    //    }
    //  },

    private String firstName;
    @Size(min = 3, max = 15)
    private String lastName;
    @Positive
    private int number;
    @PositiveOrZero
    private BigDecimal salary;
    private String position;
    private PictureDTO picture;
    private TeamDTO team;

    public PlayerImportDTO() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public PictureDTO getPicture() {
        return picture;
    }

    public TeamDTO getTeam() {
        return team;
    }
}
