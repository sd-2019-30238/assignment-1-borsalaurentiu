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
import model.Payment;
import model.User;
public class PaymentDAO {

	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO payment (paymentPlan, credit, totalAmount, name, verified) VALUES (?,?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM payment where id = ?";
	private final static String findStatementNameString = "SELECT * FROM payment where name = ?";
	private final static String findVerifiedStatementString = "SELECT * FROM payment";
	private final static String isVerifiedStatementString = "SELECT verified FROM payment where name = ?";
	private final static String updateStatementString = "UPDATE payment SET verified = ?, credit = credit - totalAmount where name = ?";
	

	public static Payment findById(int paymentId) {
		Payment toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, paymentId);
			rs = findStatement.executeQuery();
			rs.next();

			int paymentPlan = rs.getInt("paymentPlan");
			int credit = rs.getInt("crdit");
			toReturn = new Payment(paymentPlan, credit);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}


	public static Payment findByName(String name) {
		Payment toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementNameString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();

			int paymentPlan = rs.getInt("paymentPlan");
			int credit = rs.getInt("crdit");
			toReturn = new Payment(paymentPlan, credit);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}

	public static int insert(String name, Payment payment) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

			insertStatement.setInt(1, payment.getPaymentPlan());
			insertStatement.setInt(2, payment.getCredit());
			insertStatement.setInt(3, payment.getTotalAmount());
			insertStatement.setString(4, name);
			insertStatement.setString(5, "no");
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
	
	public static ArrayList<Payment> getPayments() throws SQLException{
		ArrayList<Payment> payments = new ArrayList<Payment>();
		Connection dbConnection = ConnectionFactory.getConnection();

		ResultSet rs = null;
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(findVerifiedStatementString);
			rs = findStatement.executeQuery();

			while(rs.next()) {
				Payment payment = new Payment(Integer.parseInt(rs.getString("credit")), Integer.parseInt(rs.getString("paymentPlan")), rs.getString("name"), rs.getString("verified"));
				payments.add(payment);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "PaymentDAO:getPayments " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return payments;
	}


	public static void updateStatus(String name) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, "yes");
			updateStatement.setString(2, name);
			updateStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"PaymentDAO:updateStatus " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
	}
	
	public static String isVerified(String name) {
		String toReturn = "no";

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(isVerifiedStatementString);
			findStatement.setString(1, name);
			rs = findStatement.executeQuery();
			rs.next();

			toReturn = rs.getString("verified");
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"PaymentDAO::isVerified " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
}