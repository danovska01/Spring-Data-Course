package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _6_Remove_Villain {


    private static final String GET_VILLAIN_NAME_BY_VILLAIN_ID = "SELECT `name` FROM villains WHERE id = ?;";
    private static final String REMOVE_MINIONS_VILLAINS = "DELETE FROM minions_villains WHERE villain_id = ?;";
    private static final String REMOVE_VILLAINS = "DELETE FROM villains WHERE id = ?;";
    private static final String GET_ALL_MINIONS_OF_A_VILLAIN = "SELECT COUNT(DISTINCT minion_id) AS m_count" +
            " FROM minions_villains WHERE villain_id = ?";
    private static final String REMOVE_MINIONS = "DELETE FROM minions WHERE id IN ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        Scanner sc = new Scanner(System.in);

        int villainId = Integer.parseInt(sc.nextLine());
        PreparedStatement getVillain = connection.prepareStatement(GET_VILLAIN_NAME_BY_VILLAIN_ID);
        getVillain.setInt(1, villainId);
        ResultSet villainSet = getVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println("No such villain was found");
            return;
        }
        String villainName = villainSet.getString("name");


        PreparedStatement getAllVillainMinions = connection.prepareStatement(GET_ALL_MINIONS_OF_A_VILLAIN);
        getAllVillainMinions.setInt(1, villainId);
        ResultSet minionsCountSet = getAllVillainMinions.executeQuery();
        minionsCountSet.next();
        int countRemovedMinions = minionsCountSet.getInt("m_count");

        connection.setAutoCommit(false);

        // Операции, които или трябва да минат всички или нито една!
        try {
            PreparedStatement removeMinionsVillains = connection.prepareStatement(REMOVE_MINIONS_VILLAINS);
            removeMinionsVillains.setInt(1, villainId);
            removeMinionsVillains.executeUpdate();

            PreparedStatement removeVillain = connection.prepareStatement(REMOVE_VILLAINS);
            removeVillain.setInt(1, villainId);
            removeVillain.executeUpdate();

            connection.commit();
            System.out.println(villainName + " was deleted");
            System.out.println(countRemovedMinions + " minions released");
        } catch (SQLException e) {
            connection.rollback();
        }


        connection.close();
    }
}
