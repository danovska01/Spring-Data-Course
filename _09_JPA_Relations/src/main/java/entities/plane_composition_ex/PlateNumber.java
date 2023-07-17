package entities.plane_composition_ex;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "plate_numbers")
public class PlateNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String number;

    @Column(name = "issued_at")
    private LocalDate issuedAt;

    @OneToOne(targetEntity = CompositionCar.class, mappedBy = "plateNumber")
    private CompositionCar car;

    public PlateNumber() {}

    public PlateNumber(String number, LocalDate issuedAt) {
        this.number = number;
        this.issuedAt = issuedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDate issuedAt) {
        this.issuedAt = issuedAt;
    }

    public CompositionCar getCar() {
        return car;
    }

    public void setCar(CompositionCar car) {
        this.car = car;
    }
}
