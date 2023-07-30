package bg.softuni.springjson.dtos;

import java.util.List;

public class StudentDTO {
    private final String firstName;
    private final int age;
    private final boolean isGraduated;
    private final List<String> coursesTaken;

    public StudentDTO(String firstName, int age, boolean isGraduated, List<String> coursesTaken) {
        this.firstName = firstName;
        this.age = age;
        this.isGraduated = isGraduated;
        this.coursesTaken = coursesTaken;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "firstName='" + firstName + '\'' +
                ", age=" + age +
                ", isGraduated=" + isGraduated +
                ", coursesTaken=" + coursesTaken +
                '}';
    }
}
