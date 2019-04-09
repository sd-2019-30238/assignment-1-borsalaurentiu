package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.AccountValidator;
import bll.validators.Validator;
import dao.UserDAO;
import model.User;

public class UserBLL {

	private List<Validator<User>> validators;

	public UserBLL() {
		validators = new ArrayList<Validator<User>>();
		validators.add(new AccountValidator());
	}

	public User findUserById(int id) {
		User user = UserDAO.findById(id);
		if (user == null) {
			throw new NoSuchElementException("The user with id =" + id + " was not found!");
		}
		return user;
	}

	public int findUserByName(String name) {
		int exist = UserDAO.findByName(name);
		if (exist != 0) {
			throw new NoSuchElementException("The user with name =" + name + " was found!");
		}
		return exist;
	}

	public int insertUser(User user) {
		for (Validator<User> v : validators) {
			v.validate(user);
		}
		return UserDAO.insert(user);
	}
}
