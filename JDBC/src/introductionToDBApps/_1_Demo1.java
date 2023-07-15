package introductionToDBApps;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _1_Demo1 {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);


        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "Badger3802!!");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT COUNT(g.id) AS game_count, CONCAT(u.first_name, ' ', u.last_name) AS name\n" +
                        "FROM games AS g\n" +
                        "JOIN users_games AS ug ON ug.game_id = g.id\n" +
                        "JOIN users AS u ON u.id = ug.user_id\n" +
                        "WHERE u.user_name = ?" +
                        "GROUP BY u.first_name, u.last_name;");

        System.out.println("Enter user: ");
        String userName = sc.nextLine();

        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();


        while(rs.next()){
            String name= rs.getString("name");
            int game_count = rs.getInt("game_count");
            System.out.printf("User: %s%n%s has played %d games%n", userName, name, game_count);
        }
        if(!rs.next()){
            System.out.println("No such user exists");
        }
        connection.close();


    }
}