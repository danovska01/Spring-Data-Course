import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _08_GetEmployeesWithProject {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        //Get an employee by his/her id
        int id = Integer.parseInt(scanner.nextLine());

        //Print only his/her first name, last name, job title and projects (only their names).
        // The projects should be ordered by name (ascending).

        entityManager
                .createQuery("SELECT e FROM Employee e" +
                                " WHERE e.id= :id" +
                                " ORDER BY e.firstName ASC, e.middleName ASC, e.lastName ASC",
                        Employee.class)
                .setParameter("id", id)
                .getResultStream()
                .forEach(e -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(e.getFirstName()).append(" ").append(e.getLastName())
                            .append(" - ").append(e.getJobTitle()).append("\n");

                    for (entities.Project project : e.getProjects()) {
                        sb.append(project.getName()).append("\n");
                    }

                    System.out.println(sb.toString());
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
