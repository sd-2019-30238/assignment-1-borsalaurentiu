package bll;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.PaymentValidator;
import bll.validators.Validator;
import dao.PaymentDAO;
import dao.UserDAO;
import model.Payment;
import model.User;


public class PaymentBLL {

	private List<Validator<Payment>> validators;

	public PaymentBLL() {
		validators = new ArrayList<Validator<Payment>>();
		validators.add(new PaymentValidator());
	}

	public Payment findPaymentByName(String name) {
		Payment payment = PaymentDAO.findByName(name);
		if (payment == null) {
			throw new NoSuchElementException("The user with name =" + name + " was not found!");
		}
		return payment;
	}
	
	public String isVerified(String name) {
		return PaymentDAO.isVerified(name);
	}

	public int insertPayment(String name, Payment payment) {
		for (Validator<Payment> v : validators) {
			v.validate(payment);
		}
		return PaymentDAO.insert(name, payment);
	}
	
	public ArrayList<Payment> getPayments() throws SQLException{
		ArrayList<Payment> payments = new ArrayList<Payment>();
		payments = PaymentDAO.getPayments();
		return payments;
	}

	public void updateStatus(String name) {
		PaymentDAO.updateStatus(name);
	}
}
