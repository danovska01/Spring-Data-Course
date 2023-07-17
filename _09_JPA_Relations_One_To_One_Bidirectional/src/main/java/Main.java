import entities.BasicLabel;
import entities.BasicShampoo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("KKK");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Create and persist a BasicLabel
            BasicLabel label = new BasicLabel("Label 1");
            BasicLabel label2 = new BasicLabel("Label 2");
            BasicLabel label3 = new BasicLabel("Label 3");
            entityManager.persist(label);
            entityManager.persist(label2);
            entityManager.persist(label3);

            // Create and persist a BasicShampoo with the associated BasicLabel
            BasicShampoo shampoo = new BasicShampoo("Shampoo M", label3);
            label.setShampoo(shampoo);
            entityManager.persist(shampoo);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
