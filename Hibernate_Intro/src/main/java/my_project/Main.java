package my_project;

import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Your Code Here
        Student student = new Student("Gosho", 55);
        session.save(student);

        Student student1 = session.get(Student.class, 3);
        System.out.println(student1);

        List<Student> allStudents = session
                .createQuery("FROM Student AS s WHERE s.name='Pesho2'", Student.class)
                .list();

        allStudents.forEach(System.out::println);

//
//        Student student2 = new Student( "Pesho82", 44);
//        session.save(student2);
//        System.out.println(student2);

//        Student student1 = session.get(Student.class, 7);
//        System.out.println(student1);

//        List<Student> studentList =
//        session.createQuery("FROM Student " ,
//                Student.class).list();
//        for (Student student : studentList) {
//            System.out.println(student.toString());
//        }

        session.getTransaction().commit();
        session.close();
    }
}