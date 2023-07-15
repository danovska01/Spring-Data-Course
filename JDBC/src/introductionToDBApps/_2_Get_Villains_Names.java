package introductionToDBApps;

import java.sql.*;
import java.util.Properties;


public class _2_Get_Villains_Names {
    public static void main(String[] args) throws SQLException {

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "Badger3802!!");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT v.name, COUNT(distinct mv.minion_id) AS minion_count\n" +
                        "FROM villains AS v\n" +
                        "JOIN minions_villains AS mv ON v.id = mv.villain_id\n" +
                        "JOIN minions AS m ON m.id = mv.minion_id\n" +
                        "GROUP BY v.name\n" +
                        "HAVING minion_count > 15\n" +
                        "ORDER BY minion_count DESC;");


        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            int minion_count = rs.getInt("minion_count");
            System.out.println(name + " " + minion_count);

        }

        connection.close();

    }
}
