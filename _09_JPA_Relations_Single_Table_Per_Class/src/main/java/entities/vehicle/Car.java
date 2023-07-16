package entities.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car extends Vehicle {
    private final static String type = "CAR";
    public Car(){
        super(type);
    }
}