package model;

public class Payment {
	private static final int price = 200;
	private int id;
	private int paymentPlan;
	private int credit;
	private int totalAmount;
	
	public Payment(int paymentPlan, int credit) {
		this.paymentPlan = paymentPlan;
		this.credit = credit;
		this.totalAmount = paymentPlan * price;
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
}
