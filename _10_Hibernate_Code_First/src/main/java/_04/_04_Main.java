package _04;

import _04.entities.Diagnose;
import _04.entities.Medicament;
import _04.entities.Patient;
import _04.entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class _04_Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Set<Diagnose> diagnoseSet = new HashSet<>();
        Set<Medicament> medicamentSet = new HashSet<>();

        Date dateOfBirth = new Date(1999, 10, 7);
        Patient p1 = new Patient("Gosho", "Toshev", dateOfBirth);
        entityManager.persist(p1);

        Date dateOfBirth2 = new Date(2009, 2, 17);
        Patient p2 = new Patient("Tosho", "Goshev", dateOfBirth2);
        entityManager.persist(p2);

        Diagnose d1 = new Diagnose();
        d1.setName("d1");
        entityManager.persist(d1);
        diagnoseSet.add(d1);

        Diagnose d2 = new Diagnose();
        d2.setName("d1");
        entityManager.persist(d2);
        diagnoseSet.add(d2);


        Medicament m1 = new Medicament("pill1Name");
        entityManager.persist(m1);
        medicamentSet.add(m1);

        Medicament m2 = new Medicament("pill3Name");
        entityManager.persist(m2);
        medicamentSet.add(m2);

        p1.setDiagnoses(diagnoseSet);
        p2.setDiagnoses(diagnoseSet);
        p1.setMedicaments(medicamentSet);
        p2.setMedicaments(medicamentSet);

        Visitation v1 = new Visitation();
        entityManager.persist(v1);
        v1.setPatient(p1);


        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }

}
