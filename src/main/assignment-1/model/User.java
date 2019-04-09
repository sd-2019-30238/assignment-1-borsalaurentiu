package model;

public class User {
	private int id;
	private String name;
	private String password;
	private String type;
	private int paymentPlan;
	
	public User(String name, String password, String type, int paymentPlan) {
		this.name = name;
		this.password = password;
		this.type = type;
		this.paymentPlan = paymentPlan;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public int getPaymentPlan() {
		return paymentPlan;
	}
	
	public void setPaymentPlan(int paymentPlan) {
		this.paymentPlan = paymentPlan;
	}

}
