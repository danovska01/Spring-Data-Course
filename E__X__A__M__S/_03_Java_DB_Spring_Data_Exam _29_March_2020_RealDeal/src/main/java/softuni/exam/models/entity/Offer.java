package softuni.exam.models.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private BigDecimal price;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(name = "has_gold_status")
    private boolean hasGoldStatus;
    @Column(name = "added_on")
    private String addedOn;

    //One Offer may have only one Car, and one Car may have many Offer.
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    //One Offer may have only one Seller, and one Seller may have many Offers.
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    //One Offer may have many Pictures, and one Picture may have many Offers.
    @ManyToMany
    @JoinTable(
            name = "offers_pictures",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id")
    )
    private Set<Picture> pictures;


    public Offer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }

    //The combination of description and addedOn makes an offer unique.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer offer)) return false;
        return Objects.equals(description, offer.description) && Objects.equals(addedOn, offer.addedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, addedOn);
    }
}
