package introductionToDBApps;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _5_Change_Town_Names_Casing {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String countryName = scanner.nextLine();

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "Badger3802!!");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", props);

        PreparedStatement updateTownNames = connection.prepareStatement("UPDATE towns SET name = UPPER(name) WHERE country = ?");
        updateTownNames.setString(1, countryName);

        int updatedCount = updateTownNames.executeUpdate();

        if (updatedCount <= 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.println(updatedCount + " town names were affected");

        PreparedStatement selectAllTowns = connection.prepareStatement("SELECT name FROM towns WHERE country = ?");
        selectAllTowns.setString(1, countryName);

        ResultSet townSet = selectAllTowns.executeQuery();

        List<String> towns = new ArrayList<>();
        while (townSet.next()) {
            String townName = townSet.getString("name");
            towns.add(townName);
        }

        System.out.printf("[%s]%n", String.join(", ", towns));
    }
}
