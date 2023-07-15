package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _8_Increase_Minions_Age {


    private static final String GET_UPDATED_MINIONS = "SELECT * FROM minions;";
    static String UPDATE_MINIONS = "UPDATE minions SET age = age + 1, `name` = LOWER(`name`) WHERE id IN ( ? );";

    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();

        // Operations to set appropriate amount of "?" positions.
        String[] idsToUpdate = sc.nextLine().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < idsToUpdate.length; i++) {
            if (i == idsToUpdate.length - 1) {
                sb.append("?");
                break;
            }
            sb.append("?, ");
        }
        UPDATE_MINIONS = UPDATE_MINIONS.replace("?", sb.toString());

        // UPDATE

        PreparedStatement pstmt = connection.prepareStatement(UPDATE_MINIONS);
        for (int i = 1; i <= idsToUpdate.length; i++) {
            pstmt.setString(i, idsToUpdate[i - 1]);
        }

        pstmt.executeUpdate();


        //    SELECT ALL
        final PreparedStatement selectStatement = connection.prepareStatement(GET_UPDATED_MINIONS);

        final ResultSet resultSet = selectStatement.executeQuery();

        while (resultSet.next()) {
            final String name = resultSet.getString("name");
            final int id = resultSet.getInt("id");
            int age = resultSet.getInt("age");
            System.out.println(name + " " + id + " " + age);
        }
        connection.close();
    }

}
