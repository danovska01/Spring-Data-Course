package softuni.exam.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PassengerImportDTO {

    //"firstName": "Genia",
    //    "lastName": "B",
    //    "age": 55,
    //    "phoneNumber": "+7 (527) 135-4990",
    //    "email": "gbohling0@wikipedia.org",
    //    "town": "Los Angels"
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Positive
    private int age;
    private String phoneNumber;
    @Email
    private String email;

    private String town;

    public PassengerImportDTO() {

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTown() {
        return town;
    }
}
