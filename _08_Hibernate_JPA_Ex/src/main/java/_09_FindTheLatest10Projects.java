
import entities.Project;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.stream.Collectors;

public class _09_FindTheLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("PU_Name");

        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();


        List<Project> projectList = entityManager
                .createQuery("FROM Project p" +
                                " ORDER BY p.name ASC",
                        Project.class)
                .setMaxResults(10)
                .getResultStream()
                .collect(Collectors.toList());

        //Print their name, description, start and end date

        //Project name: All-Purpose Bike Stand
        //Project Description: Research, design and
        //development of ...
        //Project Start Date:2005-09-01 00:00:00.0
        //Project End Date: null

        projectList.forEach(project -> {
            String sb = "Project name: " + project.getName() + System.lineSeparator() +
                    "Project Description: " + project.getDescription() + System.lineSeparator() +
                    "Project Start Date: " + project.getStartDate() + System.lineSeparator() +
                    "Project End Date: " + project.getEndDate() + System.lineSeparator();

            System.out.println(sb);
        });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
