package mediator;

import model.User;

public class UserColleague implements Colleague {

	private User user;
	private String type;
	
	public UserColleague(User user) {
		this.user = user;
		this.type = "addUser";
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}