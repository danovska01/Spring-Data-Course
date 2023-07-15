package my_project;

import entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("hibernate-jpa");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

//        Student student = new Student("TeoNew", 10, "School 1");
//        em.persist(student);
        Student student = new Student("LEOOOO", 18, "School 2");
        em.persist(student);
//
//        student.setName("TeoUpdated");
//        em.persist(student);

//        Student findStudent = em.find(Student.class, 5);
//        em.remove(findStudent);
//        System.out.println(findStudent.getId());

        List<Student> from_student = em
                .createQuery("FROM Student", Student.class)
                .getResultList();

        from_student.forEach(s -> System.out.println(s));

        em.getTransaction().commit();
    }

}