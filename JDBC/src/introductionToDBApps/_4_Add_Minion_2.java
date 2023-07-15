package introductionToDBApps;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _4_Add_Minion_2 {

    private static final String GET_TOWN_ID_BY_TOWN_NAME = "SELECT id FROM towns WHERE `name` = ?;";
    private static final String INSERT_TOWN = "INSERT INTO towns(name) VALUES (?);";

    private static final String GET_VILLAIN_ID_BY_VILLAIN_NAME = "SELECT id FROM villains WHERE `name` = ?;";
    private static final String INSERT_VILLAIN = "INSERT INTO villains(`name`, evilness_factor) VALUES(?, ?);";

    private static final String INSERT_MINION = "INSERT INTO minions(`name`, age, town_id) VALUES(?, ?, ?);";
    private static final String GET_MOST_RECENT_MINION = "SELECT id FROM minions ORDER BY id DESC LIMIT 1";
    private static final String INSERT_INTO_MINIONS_VILLAINS = "INSERT INTO minions_villains VALUES(?, ?);";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getSQLConnection();

        Scanner sc = new Scanner(System.in);
        String[] minionInfo = sc.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String minionTown = minionInfo[3];

        String villainName = sc.nextLine().split(" ")[1];


        /// PART 1 - Get or insert town

        PreparedStatement selectTown = connection.prepareStatement(GET_TOWN_ID_BY_TOWN_NAME);
        selectTown.setString(1, minionTown);
        ResultSet townSet = selectTown.executeQuery();

        int townId = 0;
        if (!townSet.next()) {
            PreparedStatement insertTownStatement = connection.prepareStatement(INSERT_TOWN);
            insertTownStatement.setString(1, minionTown);
            insertTownStatement.executeUpdate();
            ResultSet updatedTownSet = selectTown.executeQuery();
            updatedTownSet.next();
            townId = updatedTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.%n", minionTown);
        } else {
            townId = townSet.getInt("id");
        }


// PART 2 - Get or insert villain

        PreparedStatement getVillain = connection.prepareStatement(GET_VILLAIN_ID_BY_VILLAIN_NAME);
        getVillain.setString(1, villainName);
        ResultSet villainSet = getVillain.executeQuery();

        int villainId = 0;
        if (!villainSet.next()) {
            PreparedStatement insertVillain = connection.prepareStatement(INSERT_VILLAIN);
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");

            insertVillain.executeUpdate();

            ResultSet updatedVillainSet = getVillain.executeQuery();
            updatedVillainSet.next();
            villainId = updatedVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.%n", villainName);
        } else {
            villainId = villainSet.getInt("id");
        }


/// PART 3 - INSERT minion

        PreparedStatement insertMinion = connection.prepareStatement(INSERT_MINION);
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);

        insertMinion.executeUpdate();


/// EPILOGUE
        // A

        /// Да се има предвид, че в една реална база това може и да не сработи! Някой може да
        /// добави нов миньон и да получим различен от очаквания ред!
        PreparedStatement getLastMinion = connection.prepareStatement(GET_MOST_RECENT_MINION);
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt("id");

        /// B
        PreparedStatement insertMinionsVillains = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);
        insertMinionsVillains.setInt(1, lastMinionId);
        insertMinionsVillains.setInt(2, villainId);

        System.out.printf("Successfully added %s to be minion of %s.%n", minionName, villainName);
        connection.close();
    }
}
