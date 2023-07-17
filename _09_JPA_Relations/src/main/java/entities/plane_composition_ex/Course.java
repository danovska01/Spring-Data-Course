package entities.plane_composition_ex;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String teacher;

    @ManyToMany
    @JoinTable(name = "c_s",
        joinColumns = @JoinColumn(name = "c_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "s_id", referencedColumnName = "id"))
    private List<Student> students;

    public Course() {}

    public Course(String name, String teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
