package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bll.PaymentBLL;
import bll.UserBLL;
import model.Payment;
import model.User;

public class StaffGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTable table = new JTable();

	private JLabel nameLabel = new JLabel("Name: ");
	private JTextField nameText = new JTextField();
	private JButton authorizeButton = new JButton("Verify account!");	

	public StaffGUI() throws SQLException {
		initializeFrame();
		addComponents();
		initializeComponents();
		addListeners();
		showTable();
	}

	public void initializeFrame() {
		setTitle("Assignment 1 - Your Books Everywhere - STAFF");
		setSize(new Dimension(480, 450));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(panel);
	}

	public void showTable() throws SQLException  {
		UserBLL userBll = new UserBLL();
		ArrayList<User> users = new ArrayList<User>();
		users = userBll.getUnauthorizedUsers();
		
		PaymentBLL paymentBll = new PaymentBLL();
		ArrayList<Payment> payments = new ArrayList<Payment>();
		payments = paymentBll.getPayments();

		String[] coloane = {"Name","Credit","Total amount","Verified"};		
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("name");
		model.addColumn("credit");
		model.addColumn("totalAmount");
		model.addColumn("verified");
		model.addRow(coloane);	

		for(Payment payment: payments){
			Object[] row = new Object[4];
			row[0] = payment.getName();
			row[1] = payment.getCredit(); 
			row[2] = payment.getTotalAmount();
			row[3] = payment.getVerified();
			model.addRow(row);
		}
		
		for(User user: users){
			Object[] row = new Object[4];
			row[0] = user.getName();
			row[1] = 0;
			row[2] = user.getPaymentPlan() * Payment.getPrice();
			row[3] = "NOT PAID";
			model.addRow(row);
		}
		table.setModel(model);
	}

	public void initializeComponents() {
		authorizeButton.setBounds(100, 290, 275, 50);
		nameLabel.setBounds(100, 245, 125, 30);
		nameText.setBounds(225, 245, 150, 30);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		table.setBounds(0, 0, 480, 200);
	}

	public void addComponents() {
		panel.setLayout(null);
		panel.add(table);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(authorizeButton);
	}

	public void addListeners() {
		authorizeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				PaymentBLL paymentBll = new PaymentBLL();
				paymentBll.updateStatus(name);
				try {
					showTable();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

}
