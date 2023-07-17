import entities.composition.CompositionCar;
import entities.composition.PlateNumber;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class CompositionMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("relations");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        CompositionCar car = new CompositionCar("Opel", 1200);
        CompositionCar car2 = new CompositionCar("Skoda", 1300);

        PlateNumber number = new PlateNumber("3534", LocalDate.now());

        car.setPlateNumber(number);
        number.setCar(car2);

        em.persist(number);
        em.persist(car);
        em.persist(car2);

        CompositionCar compositionCar = em.find(CompositionCar.class, 9);
        PlateNumber plateNumber = em.find(PlateNumber.class, 9);

        em.getTransaction().commit();

        System.out.println("BREAK?");
    }
}
