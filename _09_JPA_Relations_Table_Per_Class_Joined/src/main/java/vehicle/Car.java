package vehicle;

import javax.persistence.Entity;

@Entity
public class Car extends PassengerVehicle {
    private final static String type = "CAR";

    public Car() {
    }

    public Car(String type, int numOfPassengers) {
        super(type, numOfPassengers);
        super.setType(getType());
    }

    @Override
    public String getType() {
        return type;
    }


}