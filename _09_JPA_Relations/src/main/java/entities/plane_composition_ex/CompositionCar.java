package entities.plane_composition_ex;

import javax.persistence.*;

@Entity
@Table(name = "composition_car")
public class CompositionCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String model;

    @Column
    private double price;

    @OneToOne
    @JoinColumn(name = "plate_id")
    private PlateNumber plateNumber;

    public CompositionCar() {}

    public CompositionCar(String model, double price) {
        this.model = model;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PlateNumber getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(PlateNumber plateNumber) {
        this.plateNumber = plateNumber;
    }
}
