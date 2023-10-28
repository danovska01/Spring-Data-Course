package softuni.exam.instagraphlite.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(unique = true, nullable = true)
    private String path;
    @Column(unique = true)
    private double size;

    public Picture() {

    }

    //One Picture may have many Users, but one User may have only one Picture.
    //One Post may have only one Picture, but one Picture can be in many Posts.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return String.format("%.2f – %s\n", this.getSize(), this.getPath());
    }
}
