package dtos;


import com.google.gson.annotations.Expose;

import java.util.Date;

public class CourseDTO {
    @Expose
    private String name;

    @Expose
    private int lengthInWeeks;

    @Expose
    private Date createdAt;

    public CourseDTO(String name, int lengthInWeeks, Date createdAt) {
        this.name = name;
        this.lengthInWeeks = lengthInWeeks;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "dtos.CourseDTO{" +
                "name='" + name + '\'' +
                ", lengthInWeeks=" + lengthInWeeks +
                ", createdAt=" + createdAt +
                '}';
    }
}
