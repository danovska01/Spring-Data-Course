import entities.Course;
import entities.User;
import orm.EntityManager;
import orm.config.Connector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        Connector.createConnection("root", "Badger3802!!", "soft_uni");
        Connection connection = Connector.getConnection();

        // INSERT
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        boolean persistResult = userEntityManager.persist(new User("u3", "p3", 24, LocalDate.now()));
        System.out.println(persistResult);

        //SELECT
        User first = userEntityManager.findFirst(User.class);
        System.out.println(first);


        //INSERT
        EntityManager<Course> courseEntityManager = new EntityManager<>(connection);
        courseEntityManager.persist(new Course("Math", 12));

        //SELECT
        Course first1 = courseEntityManager.findFirst(Course.class);
        System.out.println(first1);

//        EntityManager<Department> departmentEntityManager = new EntityManager<>(connection);
//        departmentEntityManager.persist(new Department());
    }
}
