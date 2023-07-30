package dtos;

import com.google.gson.annotations.Expose;

public class StudentAdditionalInfoDTO {
    @Expose
    private boolean isGraduated;

    @Expose
    private double averageGrade;

    public StudentAdditionalInfoDTO(boolean isGraduated, double averageGrade) {
        this.isGraduated = isGraduated;
        this.averageGrade = averageGrade;
    }

    @Override
    public String toString() {
        return "dtos.StudentAdditionalInfoDTO{" +
                "isGraduated=" + isGraduated +
                ", averageGrade=" + averageGrade +
                '}';
    }
}
