import vehicle.Car;
import vehicle.Truck;
import vehicle.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle truck = new Truck("Typo 1", 6, 6009);

        Vehicle car = new Car("", 4);
        Vehicle truck2 = new Truck("Typo 2", 8, 8009);
        Vehicle car2 = new Car("Alpha", 6);
        Vehicle car3 = new Car("Volvo", 5);


        entityManager.persist(truck);
        entityManager.persist(car);
        entityManager.persist(truck2);
        entityManager.persist(car2);
        entityManager.persist(car3);

        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
