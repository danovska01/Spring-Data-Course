package _05;


import _05.entities.BankAccount;
import _05.entities.CreditCard;
import _05.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Month;
import java.time.Year;

public class _05_Main {

    public static void main(String[] args) {
        // Create an EntityManagerFactory and EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Create a new user
        User user = new User("Bohn", "Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("secretpassword");

        // Create and add billing details

        BankAccount bankAccount = new BankAccount(12345, user, "SWIFT123");
        CreditCard creditCard = new CreditCard(98765, user, "Visa");
        creditCard.setExpirationMonth(Month.of(12));
        creditCard.setExpirationYear(Year.of(2025));

        user.getBillingDetails().add(bankAccount);
        user.getBillingDetails().add(creditCard);

        // Persist the user and billing details to the database
        EntityTransaction transaction = em.getTransaction();
        try {
            if (!transaction.isActive()) {
                transaction.begin();
            }
            em.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }


}
