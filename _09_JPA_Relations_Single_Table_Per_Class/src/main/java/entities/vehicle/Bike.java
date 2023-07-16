package entities.vehicle;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bikes")
public class Bike extends Vehicle {
    private final static String type = "BIKE";
    public Bike(){
        super(type);
    }
}