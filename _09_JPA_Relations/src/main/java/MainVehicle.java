import entities.vehicle_Inheritance_ex.Bike;
import entities.vehicle_Inheritance_ex.Car;
import entities.vehicle_Inheritance_ex.Truck;
import entities.vehicle_Inheritance_ex.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainVehicle {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle bike = new Bike();
        Vehicle car = new Car();
        Vehicle truck = new Truck();


        entityManager.persist(bike);
        entityManager.persist(car);
        entityManager.persist(truck);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
