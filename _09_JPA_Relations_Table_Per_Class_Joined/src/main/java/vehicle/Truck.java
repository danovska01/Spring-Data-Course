package vehicle;

import javax.persistence.Entity;

@Entity
public class Truck extends TransportationVehicle {
    private final static String type = "TRUCK";


    private int numOfContainers;

    public Truck() {
    }

    public Truck(String type, int numOfContainers,
                 int loadCapacity) {
        super(type, loadCapacity);
        super.setType(getType());
        this.numOfContainers = numOfContainers;

    }

    public int getNumOfContainers() {
        return numOfContainers;
    }

    public void setNumOfContainers(int numOfContainers) {
        this.numOfContainers = numOfContainers;
    }

    @Override
    public String getType() {
        return type;
    }


}
