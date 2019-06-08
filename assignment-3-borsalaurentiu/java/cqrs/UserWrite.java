package cqrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import mediator.Colleague;
import mediator.Handler;
import mediator.Mediator;
import mediator.UserColleague;
import mediator.UserHandler;
import model.User;

public class UserWrite {
	private Mediator mediator;
	
	public UserWrite(Mediator mediator) {
		this.mediator = mediator;
	}

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	public String addUser(User user)
	{
		Colleague colleague = new UserColleague(user);
		Handler handler = new UserHandler();
		mediator.registerHandler(handler);
		return mediator.handle(colleague);
	}
	
	private static final String insertStatementString = "INSERT INTO user (name, password, type, paymentPlan) VALUES (?,?,?,?)";
	protected static final Logger LOGGER = Logger.getLogger(UserWrite.class.getName());

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
}