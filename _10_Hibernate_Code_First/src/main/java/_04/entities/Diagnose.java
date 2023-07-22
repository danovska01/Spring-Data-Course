package _04.entities;

import javax.persistence.*;

@Entity(name = "_04_diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "diagnose_name", nullable = false)
    private String name;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Diagnose() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
