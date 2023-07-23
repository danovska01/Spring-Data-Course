import entities.plane_composition_ex.Company;
import entities.plane_composition_ex.Plane;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class Composition_Plane_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Company company = new Company("with planes new2");

//        Plane plane6 = em.find(Plane.class, 6);
//        Plane plane7 = em.find(Plane.class, 7);
//        Plane plane8 = em.find(Plane.class, 8);

        Plane new1 = new Plane("asda", 12);
        Plane new2 = new Plane("sadas", 14);

        List<Plane> fleet = new ArrayList<>();
        fleet.add(new1);
        fleet.add(new2);

        company.setPlanes(fleet);

        em.persist(company);
        em.getTransaction().commit();
    }
}
