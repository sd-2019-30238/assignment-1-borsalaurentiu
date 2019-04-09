package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.PaymentValidator;
import bll.validators.Validator;
import dao.PaymentDAO;
import model.Payment;


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

	public int insertPayment(String name, Payment payment) {
		for (Validator<Payment> v : validators) {
			v.validate(payment);
		}
		return PaymentDAO.insert(name, payment);
	}
}
