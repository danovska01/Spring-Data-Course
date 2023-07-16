import entities.vehicle.Bike;
import entities.vehicle.Car;
import entities.vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle bike = new Bike();
        Vehicle car = new Car();


        entityManager.persist(bike);
        entityManager.persist(car);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
