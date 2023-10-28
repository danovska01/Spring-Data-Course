package softuni.exam.instagraphlite.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserImportDTO {
    //    "username": "UnderSinduxrein",
    //    "password": "4l8nYGTKMW",
    //    "profilePicture": "InvalidPicturePath"


    @NotNull
    @Size(min = 2, max = 18)
    private String username;
    @NotNull
    @Size(min = 4)
    private String password;

    @NotNull
    private String profilePicture;

    public UserImportDTO() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
