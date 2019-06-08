package mediator;

import cqrs.UserWrite;
import model.User;

public class UserHandler implements Handler{	
	private String type;

	public UserHandler() {
		this.setType("addUser");
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public String handle(Colleague colleague) {
		User user = ((UserColleague) colleague).getUser();
		if(UserWrite.insert(user))
			return "success";
		else
			return "failure";
	}
}
