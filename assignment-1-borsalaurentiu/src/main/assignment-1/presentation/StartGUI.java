package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bll.UserBLL;
import model.User;

public class StartGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	private JLabel nameLabel = new JLabel("Name: ");
	private JLabel passwordLabel = new JLabel("Password: ");
	private JLabel orLabel = new JLabel("OR");

	private JTextField nameText = new JTextField();
	private JTextField passwordText = new JPasswordField();

	private JButton createAccountButton = new JButton("Create account");
	private JButton loginButton = new JButton("Login");

	public StartGUI() {
		initializeFrame();
		addComponents();
		initializeComponents();
		addListeners();
	}

	public void initializeFrame() {
		setTitle("Assignment 1 - Your Books Everywhere");
		setSize(new Dimension(730, 450));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(panel);
	}

	public void initializeComponents() {
		loginButton.setBounds(225, 175, 275, 50);
		createAccountButton.setBounds(225, 290, 275, 50);
		nameLabel.setBounds(225, 70, 125, 30);
		nameText.setBounds(350, 70, 150, 30);

		orLabel.setBounds(225, 245, 275, 30);

		passwordLabel.setBounds(225, 115, 125, 30);
		passwordText.setBounds(350, 115, 150, 30);

		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		orLabel.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void addComponents() {
		panel.setLayout(null);
		panel.add(createAccountButton);
		panel.add(loginButton);
		panel.add(nameLabel);
		panel.add(passwordLabel);
		panel.add(nameText);
		panel.add(passwordText);
		panel.add(orLabel);
	}

	public void addListeners() {

		createAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAccountGUI createAccountGUI;
				createAccountGUI = new CreateAccountGUI();
				createAccountGUI.setVisible(true);
			}
		});

		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI loginGUI;
				RecommendGUI recommendGUI;
				StaffGUI staffGUI;
				String name = nameText.getText();
				String password = passwordText.getText();

				User user = new User(name, password);
				UserBLL userBLL = new UserBLL();

				if(userBLL.isUser(user) == true) {
					if(name.contains("STAFF")) {
						try {
							staffGUI = new StaffGUI();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						try {
							loginGUI = new LoginGUI(user);
							loginGUI.setVisible(true);
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							recommendGUI = new RecommendGUI();
							recommendGUI.setVisible(true);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}
			}
		});
	}

	public static void main(String[] args) {
		new StartGUI();
	}
}
