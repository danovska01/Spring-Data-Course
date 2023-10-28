package softuni.exam.models.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


public class Agent_Import_DTO {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;

    private String town;
    @Email
    private String email;


    public Agent_Import_DTO() {

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

    public String getTown() {
        return town;
    }
}
