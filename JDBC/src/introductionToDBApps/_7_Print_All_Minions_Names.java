package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class _7_Print_All_Minions_Names {


    private static final String GET_ALL_MINIONS_NAMES = "SELECT * from minions;";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement statement = connection.prepareStatement(GET_ALL_MINIONS_NAMES);
        final ResultSet resultSet = statement.executeQuery();


        List<String> minionsNames = new ArrayList<>();
        List<String> ordered = new ArrayList<>();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            minionsNames.add(name);
        }

        int count = 0;

        while (minionsNames.size() != 0) {
            String nameToRemove = "";
            if (count % 2 == 0) {
                nameToRemove = minionsNames.get(0);
                minionsNames.remove(0);
            } else {
                nameToRemove = minionsNames.get(minionsNames.size() - 1);
                minionsNames.remove(minionsNames.size() - 1);
            }
            ordered.add(nameToRemove);
            count++;
        }


        ordered.forEach(System.out::println);


        connection.close();


    }
}
