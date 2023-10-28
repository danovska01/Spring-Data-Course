package softuni.exam.models.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
    @Column(name = "date_and_time", nullable = false)
    private String dateAndTime;

    //One Picture may have only one Car, and one Car may have many Pictures.
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    //One Offer may have many Pictures, and one Picture may have many Offers.
    @ManyToMany(mappedBy = "pictures")
    private Set<Offer> offers;

    public Picture() {

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

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture picture)) return false;
        return Objects.equals(id, picture.id) && Objects.equals(name, picture.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
