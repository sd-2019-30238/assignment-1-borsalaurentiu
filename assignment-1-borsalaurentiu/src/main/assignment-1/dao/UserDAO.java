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
import model.User;
public class UserDAO {

	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO user (name, password, type, paymentPlan) VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM user where id = ?";
	private final static String findNameStatementString = "SELECT id FROM user where name = ?";
	private final static String findPasswordStatementString = "SELECT password FROM user where name = ?";
	private final static String findVerifiedStatementString = "SELECT * FROM user where type = ?";
	private final static String findUnauthorizedStatementString = "SELECT * FROM user WHERE name NOT IN (SELECT name FROM payment)";
	
	public static User findById(int userId) {
		User toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, userId);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String password = rs.getString("password");
			String type = rs.getString("type");
			int paymentPlan = rs.getInt("paymentPlan");
			toReturn = new User(name, password, type, paymentPlan);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int findByName(String name) {
		int toReturn = 0;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findNameStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();

			int id = rs.getInt("id");
			toReturn = id;
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING,"UserDAO:findByName " + e.getMessage()); -- nu face parte din tabel
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static String findPassword(User user) {
		String toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findPasswordStatementString);
			findStatement.setString(1, user.getName());
			rs = findStatement.executeQuery();
			rs.next();

			String password = rs.getString("password");
			toReturn = password;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:isUser " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static int insert(User user) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

			insertStatement.setString(1, user.getName());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getType());
			insertStatement.setInt(4, user.getPaymentPlan());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}
	
	public static ArrayList<User> getUsers(String type) throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		Connection dbConnection = ConnectionFactory.getConnection();

		ResultSet rs = null;
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(findVerifiedStatementString);
			findStatement.setString(1, "user");
			rs = findStatement.executeQuery();

			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("type"), Integer.parseInt(rs.getString("paymentPlan")));
				users.add(user);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:getUsers " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return users;
	}
	
	public static ArrayList<User> getUnauthorizedUsers() throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		Connection dbConnection = ConnectionFactory.getConnection();

		ResultSet rs = null;
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(findUnauthorizedStatementString);
			rs = findStatement.executeQuery();

			while(rs.next()) {
				User user = new User(rs.getString("name"), rs.getString("type"), Integer.parseInt(rs.getString("paymentPlan")));
				users.add(user);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:getUsers " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return users;
	}
}