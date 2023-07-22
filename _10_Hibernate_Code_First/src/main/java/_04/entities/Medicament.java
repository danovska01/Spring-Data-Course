package _04.entities;

import javax.persistence.*;

@Entity(name = "_04_medicaments")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "medicament_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Medicament(String name) {
        this.name = name;
    }

    public Medicament() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}
