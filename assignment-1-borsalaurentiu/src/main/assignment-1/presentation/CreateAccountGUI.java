package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bll.PaymentBLL;
import bll.UserBLL;
import model.Payment;
import model.User;

public class CreateAccountGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	private JButton createAccountButton = new JButton("Create account");

	private JLabel nameLabel = new JLabel("Name: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	private JLabel paymentPlanLabel = new JLabel("Payment plan: ");
	private JLabel creditLabel = new JLabel("Credit: ");

	private JTextField nameText = new JTextField();
	private JTextField passwordText = new JPasswordField();
	private JTextField creditText = new JTextField();

	private JRadioButton monthlyRadioButton = new JRadioButton("1 month");
	private JRadioButton midRadioButton = new JRadioButton("6 months");
	private JRadioButton yearlyRadioButton = new JRadioButton("12 months");

	private ButtonGroup group = new ButtonGroup();

	public CreateAccountGUI() {
		initializeFrame();
		addComponents();
		initializeComponents();
		addListeners();
	}

	public void initializeFrame() {
		setTitle("Assignment 1 - Your Books Everywhere - Create account");
		setSize(new Dimension(730, 450));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(panel);
	}

	public void initializeComponents() {
		createAccountButton.setBounds(225, 290, 275, 50);

		nameLabel.setBounds(225, 70, 125, 30);
		nameText.setBounds(350, 70, 150, 30);

		passwordLabel.setBounds(225, 115, 125, 30);
		passwordText.setBounds(350, 115, 150, 30);

		creditLabel.setBounds(225, 245, 125, 30);
		creditText.setBounds(350, 245, 150, 30);

		paymentPlanLabel.setBounds(225, 180, 125, 30);
		monthlyRadioButton.setBounds(350, 155, 125, 30);
		midRadioButton.setBounds(350, 180, 150, 30);
		yearlyRadioButton.setBounds(350, 205, 150, 30);

		monthlyRadioButton.setActionCommand("1");
		midRadioButton.setActionCommand("6");
		yearlyRadioButton.setActionCommand("12");

		group.add(monthlyRadioButton);
		group.add(midRadioButton);
		group.add(yearlyRadioButton);

		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		paymentPlanLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		creditLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public void addComponents() {
		panel.setLayout(null);
		panel.add(createAccountButton);
		panel.add(nameLabel);
		panel.add(passwordLabel);
		panel.add(paymentPlanLabel);
		panel.add(creditLabel);
		panel.add(nameText);
		panel.add(passwordText);
		panel.add(creditText);
		panel.add(monthlyRadioButton);
		panel.add(midRadioButton);
		panel.add(yearlyRadioButton);
	}

	public void addListeners() {
		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String password = passwordText.getText();
				int credit = Integer.parseInt(creditText.getText());	
				int paymentPlan = 0;				
				if(monthlyRadioButton.isSelected())
					paymentPlan = 1;
				else if(midRadioButton.isSelected())
					paymentPlan = 6;
				else if(yearlyRadioButton.isSelected())
					paymentPlan = 12;

				User user = new User(name, password, "user", paymentPlan);
				UserBLL userBLL = new UserBLL();

				Payment payment = new Payment(paymentPlan, credit);
				PaymentBLL paymentBLL = new PaymentBLL();

				if(userBLL.insertUser(user) != -1)
					System.out.println(paymentBLL.insertPayment(name, payment));

			}
		});
	}
}
