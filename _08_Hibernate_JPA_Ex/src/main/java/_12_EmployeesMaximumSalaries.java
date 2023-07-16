import entities.Department;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class _12_EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        List<Department> departments = entityManager
                .createQuery("SELECT d FROM Department d", Department.class)
                .getResultList();

        //Write a program that finds the max salary for each department.
        for (Department department : departments) {
            BigDecimal maxSalary = (BigDecimal) entityManager
                    .createQuery("SELECT MAX(e.salary) FROM Employee e WHERE e.department = :department")
                    .setParameter("department", department)
                    .getSingleResult();

            // Filter the departments, which max salaries are NOT in the range between 30000 and 70000.
            if (maxSalary != null && (maxSalary.compareTo(BigDecimal.valueOf(30000)) < 0 || maxSalary.compareTo(BigDecimal.valueOf(70000)) > 0)) {
                System.out.println("Department: " + department.getName());
                System.out.println("Max Salary: " + maxSalary);
                System.out.println("----------------------");
            }
        }


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
