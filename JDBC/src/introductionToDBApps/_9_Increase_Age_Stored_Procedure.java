package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class _9_Increase_Age_Stored_Procedure {
    private static final String CALL_PROCEDURE = "CALL usp_get_older(?);";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        final Connection connection = Utils.getSQLConnection();
        int minion_id = Integer.parseInt(sc.nextLine());
        final PreparedStatement callStatement = connection.prepareStatement(CALL_PROCEDURE);
        callStatement.setInt(1, minion_id);
        callStatement.execute();

    }
}

// Creating the procedure in Workbench
/*
DELIMITER $$
CREATE PROCEDURE usp_get_older (minion_id INT)
BEGIN
UPDATE minions SET age = age + 1 WHERE id = minion_id;
END$$

 */



