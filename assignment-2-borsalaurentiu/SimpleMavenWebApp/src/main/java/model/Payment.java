package model;

public class Payment {
	private static final int price = 200;
	private int id;
	private int paymentPlan;
	private int credit;
	private int totalAmount;
	private String name;
	private String verified;
	
	public Payment(int paymentPlan, int credit) {
		this.paymentPlan = paymentPlan;
		this.credit = credit;
		this.totalAmount = paymentPlan * price;
	}

	public Payment(int credit, int paymentPlan, String name, String verified) {
		this.credit = credit;
		this.totalAmount = paymentPlan * price;
		this.name = name;
		this.verified = verified;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPaymentPlan() {
		return paymentPlan;
	}
	public void setPaymentPlan(int paymentPlan) {
		this.paymentPlan = paymentPlan;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public static int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}
}
