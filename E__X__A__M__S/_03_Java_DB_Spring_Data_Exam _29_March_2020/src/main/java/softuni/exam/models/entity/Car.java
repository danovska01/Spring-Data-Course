package softuni.exam.models.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String make;
    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private int kilometers;
    @Column(name = "registered_on", nullable = false)
    private String registeredOn;

    //One Picture may have only one Car, and one Car may have many Pictures.
    //One Offer may have only one Car, and one Car may have many Offer.
    @OneToMany(mappedBy = "car", targetEntity = Picture.class)
    private Set<Picture> pictures;

    @OneToMany(mappedBy = "car", targetEntity = Offer.class)
    private Set<Offer> offers;


    public Car() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(String registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    //The combination of make, model and kilometers makes a car unique.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car car)) return false;
        return kilometers == car.kilometers && Objects.equals(make, car.make) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, kilometers);
    }
}
