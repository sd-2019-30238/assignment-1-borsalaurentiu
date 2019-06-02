package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.WaitingList;

public class WaitingListDAO {
	protected static final Logger LOGGER = Logger.getLogger(WaitingListDAO.class.getName());
	private final static String findStatementString = "SELECT title, name, position FROM waiting";

	public static ArrayList<WaitingList> getWaitingList(String title) {
		ArrayList<WaitingList> waitingList = new ArrayList<WaitingList>();
		Connection dbConnection = ConnectionFactory.getConnection();

		ResultSet rs = null;
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();

			while(rs.next()) {
				WaitingList waiting = new WaitingList(rs.getString("title"), rs.getString("name"), Integer.parseInt(rs.getString("position")));
				if(waiting.getPosition() != -1 && waiting.getTitle().equals(title))
					waitingList.add(waiting);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "WaitingListDAO:getWaitingList " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return waitingList;
	}

}
