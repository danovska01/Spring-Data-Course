import entities.inheritence.Bike;
import entities.inheritence.Car;
import entities.inheritence.Truck;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class InheritenceMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("relations");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(new Car());
        em.persist(new Bike());
        em.persist(new Truck(10));

        Car car = em.find(Car.class, 1);
        Car car2 = em.find(Car.class, 2);


        em.getTransaction().commit();
    }
}
