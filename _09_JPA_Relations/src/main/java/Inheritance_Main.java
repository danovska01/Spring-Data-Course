import entities.vehicle_inheritance.Bike;
import entities.vehicle_inheritance.Car;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Inheritance_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(new Car(4));
        em.persist(new Bike(6));
        //em.persist(new Truck(10,10));

        Car car = em.find(Car.class, 1);
        Car car2 = em.find(Car.class, 2);



        em.getTransaction().commit();
    }
}
