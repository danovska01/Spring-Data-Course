package entities;

import orm.anotations.Column;
import orm.anotations.Entity;
import orm.anotations.Id;

@Entity(name = "courses")
public class Course {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "length_in_weeks")
    private int weeksLength;


    public Course(String name, int weeksLength) {
        this.name = name;
        this.weeksLength = weeksLength;
    }

    public Course() {

    }

    @Override
    public String toString() {
        return "Courses{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weeksLength=" + weeksLength +
                '}';
    }
}
