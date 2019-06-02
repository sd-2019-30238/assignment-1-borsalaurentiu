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
	private static final String validateStatementString = "select * from user where name=? and password=?";
	private static final String insertStatementString = "INSERT INTO user (name, password, type, paymentPlan) VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM user where id = ?";
	private final static String findNameStatementString = "SELECT id FROM user where name = ?";
	private final static String findPasswordStatementString = "SELECT password FROM user where name = ?";
	private final static String findVerifiedStatementString = "SELECT * FROM user where type = ?";
	private final static String findUnauthorizedStatementString = "SELECT * FROM user WHERE name NOT IN (SELECT name FROM payment)";

	public static boolean validate(String name, String password){  
		Connection dbConnection = ConnectionFactory.getConnection();
		System.out.println(dbConnection);
		PreparedStatement validateStatement = null;
		boolean status = false;

		try{  
			validateStatement = dbConnection.prepareStatement(validateStatementString, Statement.RETURN_GENERATED_KEYS);
			validateStatement.setString(1,name);  
			validateStatement.setString(2,password);  
			ResultSet resultSet = validateStatement.executeQuery();  
			status = resultSet.next();  
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "LoginDao:validate " + e.getMessage());
		} finally {
			ConnectionFactory.close(validateStatement);
			ConnectionFactory.close(dbConnection);
		}
		System.out.println(status);
		return status;
	}
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

	public static boolean findByName(String name) {
		boolean status = false;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findNameStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			status = rs.next();  
		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING,"UserDAO:findByName " + e.getMessage()); -- nu face parte din tabel
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return status;
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
	
	public static boolean insert(User user) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		boolean status = false;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

			insertStatement.setString(1, user.getName());
			insertStatement.setString(2, user.getPassword());
			insertStatement.setString(3, user.getType());
			insertStatement.setInt(4, user.getPaymentPlan());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			status = rs.next();  
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return status;
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