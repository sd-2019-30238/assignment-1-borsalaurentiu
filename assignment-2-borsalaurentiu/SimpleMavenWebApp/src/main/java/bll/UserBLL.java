package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.AccountValidator;
import bll.validators.PasswordValidator;
import bll.validators.Validator;
import dao.UserDAO;
import model.User;

public class UserBLL {

	private List<Validator<User>> validators;
	private List<Validator<User>> loginValidators;

	public UserBLL() {
		validators = new ArrayList<Validator<User>>();
		validators.add(new AccountValidator());

		loginValidators = new ArrayList<Validator<User>>();
		loginValidators.add(new PasswordValidator());
	}

	public User findUserById(int id) {
		User user = UserDAO.findById(id);
		if (user == null) {
			throw new NoSuchElementException("The user with id =" + id + " was not found!");
		}
		return user;
	}
	
	public String findPassword(User user) {
		String password = UserDAO.findPassword(user);
		if (password == null) {
			throw new NoSuchElementException("The user with name =" + user.getName() + " was not found!");
		}
		return password;
	}

	public int findUserByName(String name) {
		int exist = 1; //UserDAO.findByName(name);
		if (exist != 0) {
			throw new NoSuchElementException("The user with name =" + name + " was found!");
		}
		return exist;
	}

	public boolean isUser(User user) {
		for (Validator<User> v : loginValidators) {
			v.validate(user);
		}
		return true;
	}
	public boolean insertUser(User user) {
		if(!UserDAO.findByName(user.getName()))
			return UserDAO.insert(user);
		else
			return false;
	}

	public ArrayList<User> getUsers(String type) throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		users = UserDAO.getUsers(type);
		return users;
	}

	public ArrayList<User> getUnauthorizedUsers() throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		users = UserDAO.getUnauthorizedUsers();
		return users;
	}
	
}
