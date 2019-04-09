package start;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.UserBLL;
import model.User;
import presentation.StartGUI;

public class Start {
	private static int index = 0;
	protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

	public static void main(String[] args) throws SQLException {		
		User user = new User(++index, "Borsa", "parolaa", "user", 12);
		System.out.print(index);
		UserBLL userBll = new UserBLL();
		int id = userBll.insertUser(user);
		if (id > 0) {
			//		studentBll.findStudentById(id);
			System.out.println(userBll.findUserById(1));
		}


		// Generate error
		try {
			userBll.findUserById(1);
		} catch (Exception ex) {
			LOGGER.log(Level.INFO, ex.getMessage());
		}


		//obtain field-value pairs for object through reflection
		//		ReflectionExample.retrieveProperties(student);
	}



}
