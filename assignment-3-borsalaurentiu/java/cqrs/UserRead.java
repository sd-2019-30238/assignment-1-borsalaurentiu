package cqrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class UserRead {
	protected static final Logger LOGGER = Logger.getLogger(UserRead.class.getName());
	private static final String validateStatementString = "select * from user where name=? and password=?";

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
}