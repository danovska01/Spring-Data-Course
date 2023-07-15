package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _5_Change_Towns_Names_Casing_2 {

    private static final String UPDATE_TOWNS_WITH_GIVEN_NAME = "UPDATE towns SET `name` = UPPER(`name`) WHERE country = ?;";
    private static final String GET_CITIES_BY_COUNTRY_NAME = "SELECT `name` FROM towns WHERE country = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        Scanner sc = new Scanner(System.in);
        String countryName = sc.nextLine();

        PreparedStatement updateTownNames = connection.prepareStatement(UPDATE_TOWNS_WITH_GIVEN_NAME);
        updateTownNames.setString(1, countryName);
        int countUpdated = updateTownNames.executeUpdate();

        if (countUpdated == 0) {
            System.out.println("No town names were affected.");
            return;
        }

        System.out.printf("%s town names were affected.%n", countUpdated);
        PreparedStatement getAllTowns = connection.prepareStatement(GET_CITIES_BY_COUNTRY_NAME);
        getAllTowns.setString(1, countryName);
        ResultSet townSet = getAllTowns.executeQuery();

        List<String> towns = new ArrayList<>();
        while (townSet.next()) {
            towns.add(townSet.getString("name"));
        }
        System.out.println(towns);
        connection.close();
    }
}