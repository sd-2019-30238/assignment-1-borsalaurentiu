package bll.validators;

import model.Payment;

public class PaymentValidator implements Validator<Payment> {
	
	public void validate(Payment payment) {
		if (payment.getCredit() < payment.getTotalAmount()) {
			throw new IllegalArgumentException("The payment was not authorised!");
		}
	}
}
