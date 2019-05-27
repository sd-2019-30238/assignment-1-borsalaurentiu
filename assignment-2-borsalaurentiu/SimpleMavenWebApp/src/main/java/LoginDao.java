import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class LoginDao {  
	protected static final Logger LOGGER = Logger.getLogger(LoginDao.class.getName());
	private static final String validateStatementString = "select * from user where name=? and password=?";

	public static boolean validate(String name, String pass){  
		Connection dbConnection = ConnectionFactory.getConnection();
		System.out.println(dbConnection);
		PreparedStatement validateStatement = null;
		boolean status = false;

		try{  
			validateStatement = dbConnection.prepareStatement(validateStatementString, Statement.RETURN_GENERATED_KEYS);
			validateStatement.setString(1,name);  
			validateStatement.setString(2,pass);  
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