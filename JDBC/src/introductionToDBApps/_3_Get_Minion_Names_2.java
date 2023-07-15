package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _3_Get_Minion_Names_2 {
    private static final String GET_MINIONS_NAMES = "SELECT name, m.age FROM minions AS m" +
            " JOIN minions_villains AS mv ON mv.minion_id = m.id" +
            " WHERE mv.villain_id = ?";
    private static final String GET_VILLAIN_NAME = "SELECT `name` FROM villains WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        Scanner sc = new Scanner(System.in);
        int villainId = Integer.parseInt(sc.nextLine());

        final PreparedStatement villainStatement = connection.prepareStatement(GET_VILLAIN_NAME);
        villainStatement.setInt(1, villainId);
        ResultSet villainResult = villainStatement.executeQuery();

        if (!villainResult.next()) {
            System.out.println("No villain with ID " + villainId + " exists in the database.");
            return;
        }
        System.out.println("Villain: " + villainResult.getString("name"));

        PreparedStatement minionsStatement = connection.prepareStatement(GET_MINIONS_NAMES);
        minionsStatement.setInt(1, villainId);
        ResultSet minionsResult = minionsStatement.executeQuery();

        for (int i = 1; minionsResult.next(); i++) {
            System.out.println(i + ". " + minionsResult.getString("name") + " "
                    + minionsResult.getInt("age"));
        }
        connection.close();
    }
}
