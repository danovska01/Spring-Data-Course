import entities.User;
import orm.EntityManager;
import orm.config.Connector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class Main_Third {
    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Connector.createConnection("root", "Badger3802!!", "custom_orm");
        Connection connection = Connector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        //print all users before deleting
        Iterable<User> allUsers = userEntityManager.find(User.class);
        System.out.println(allUsers.toString());

        //point user for delete
        User toDelete = userEntityManager.findFirst(User.class, "id=1");
        System.out.println(toDelete);

        //delete user
        userEntityManager.delete(toDelete);

        //print all users after deleting
        Iterable<User> allUsersAfterDEL = userEntityManager.find(User.class);
        System.out.println(allUsersAfterDEL.toString());
    }
}
