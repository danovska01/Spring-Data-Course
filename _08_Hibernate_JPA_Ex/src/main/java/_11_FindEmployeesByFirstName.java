import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _11_FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String startsWith = scanner.nextLine();

        List<Employee> employeesWhoseNamesStartsWith = entityManager
                .createQuery("FROM Employee e" +
                                " WHERE e.firstName LIKE :first_name",
                        Employee.class)
                .setParameter("first_name", startsWith + "%")
                .getResultList();

        //Print their first and last names, their job title and salary
        //Sariya Harnpadoungsataya - Marketing Specialist - ($16128.00)
        //Sandra Reategui Alayo - Production Technician - ($9500.00) ...

        for (Employee employee : employeesWhoseNamesStartsWith) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName()
                    + " - " + employee.getJobTitle() + " - ($" + employee.getSalary() + ")");
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
