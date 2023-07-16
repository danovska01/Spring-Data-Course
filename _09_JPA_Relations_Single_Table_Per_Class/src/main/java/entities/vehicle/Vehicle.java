package entities.vehicle;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Basic
    private String type;
    protected Vehicle() {}
    protected Vehicle(String type) {
        this.type = type;
    }
}