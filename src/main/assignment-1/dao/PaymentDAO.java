package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Payment;
public class PaymentDAO {

	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO payment (paymentPlan, credit, totalAmount, name) VALUES (?,?,?,?)";
	private final static String findStatementString = "SELECT * FROM payment where id = ?";
	private final static String findStatementNameString = "SELECT * FROM payment where name = ?";

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
}