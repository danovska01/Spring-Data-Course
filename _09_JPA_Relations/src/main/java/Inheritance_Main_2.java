import entities.vehicle_inheritance.Bike;
import entities.vehicle_inheritance.Car;
import entities.vehicle_inheritance.Truck;
import entities.vehicle_inheritance.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Inheritance_Main_2 {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU");

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
