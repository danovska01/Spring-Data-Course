package orm.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/%s";

    private static Connection connection;

    private Connector() {
    }

    public static void createConnection(String userName, String password, String dbname) throws SQLException {


        Properties userPassProps = new Properties();
        userPassProps.setProperty("user", userName);
        userPassProps.setProperty("password", password);

        connection = DriverManager.getConnection(String.format(CONNECTION_URL, dbname), userPassProps);
    }

    public static Connection getConnection() {
        return connection;
    }
}
