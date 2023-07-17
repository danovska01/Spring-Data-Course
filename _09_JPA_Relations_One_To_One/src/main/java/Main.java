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
            BasicLabel label1 = new BasicLabel("Label 1K");
            BasicLabel label2 = new BasicLabel("Label 2KF");
            BasicLabel label3 = new BasicLabel("Label 1KJ");
            entityManager.persist(label1);
            entityManager.persist(label2);
            entityManager.persist(label3);




            // Create and persist a BasicShampoo with the associated BasicLabel
            BasicShampoo shampoo1 = new BasicShampoo("Shampoo A", label1);
            BasicShampoo shampoo2 = new BasicShampoo("Shampoo B", label2);
            BasicShampoo shampoo3 = new BasicShampoo("Shampoo C", label3);

            entityManager.persist(shampoo1);
            entityManager.persist(shampoo2);
            entityManager.persist(shampoo3);

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
