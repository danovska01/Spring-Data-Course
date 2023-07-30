package dtos;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;


public class StudentDTO implements Serializable {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    @Expose
    private StudentAdditionalInfoDTO additionalInfo;

    @Expose
    private List<CourseDTO> courses;

    public StudentDTO(String firstName, String lastName, int age, StudentAdditionalInfoDTO additionalInfo, List<CourseDTO> courses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.additionalInfo = additionalInfo;
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", additionalInfo=" + additionalInfo +
                ", courses=" + courses +
                '}';
    }
}
