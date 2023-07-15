import entities.Address;
import orm.EntityManager;
import orm.config.Connector;

import java.sql.Connection;
import java.sql.SQLException;

public class Main_Fourth {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Connector.createConnection("root", "Badger3802!!", "custom_orm");
        Connection connection = Connector.getConnection();

        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);

        // CREATE TABLE
//       addressEntityManager.doCreate(Address.class);

        // INSERT
//        boolean persistResult = addressEntityManager.persist(new Address("Bayron", 14, "Vg", "Bg", "4567"));
//        System.out.println(persistResult);

//        boolean persistResult = addressEntityManager.persist(new Address("Yyron", 19, "Sf", "Bg", "4567"));
//        System.out.println(persistResult);
    }
}
