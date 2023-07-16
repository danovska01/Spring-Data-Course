import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _13_RemoveTowns {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the town name to delete: ");
        String townName = scanner.nextLine();

        // !!!! The foreign key constraint on the address_id column in the employees table is preventing
        // the deletion of the addresses.

        // I do not know how to fix it
        // One possible decision is to remove the foreign key constraint between the address_id column
        // in the employees table and the address_id column in the addresses table.
        // This will allow deleting addresses without affecting the employees table.

        //BUT !!! removing the foreign key constraint might lead to data integrity issues if the relationship
        // between addresses and employees is essential for the application.

        Town town = entityManager
                .createQuery("SELECT t FROM Town t WHERE t.name = :townName", Town.class)
                .setParameter("townName", townName)
                .getSingleResult();

        int deletedAddressCount = entityManager
                .createQuery("DELETE FROM Address a WHERE a.town = :town")
                .setParameter("town", town)
                .executeUpdate();

        entityManager.remove(town);

        entityManager.getTransaction().commit();
        entityManager.close();

        System.out.println("Number of addresses deleted: " + deletedAddressCount);
    }
}
