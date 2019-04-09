package presentation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JButton createAccountButton = new JButton("Create account");
	private JButton loginButton = new JButton("Login");

//	private JLabel titularLabel = new JLabel("ID titular: ");
//	private JLabel contLabel = new JLabel("ID cont: ");
//	private JLabel sumaLabel = new JLabel("Suma: ");
//	private JTextField titularText = new JTextField();
//	private JTextField contText = new JTextField();
//	private JTextField sumaText = new JTextField();

	public LoginGUI() {
		initializeFrame();
		addComponents();
		initializeComponents();
		addListeners();
	}

	public void initializeFrame() {
		setTitle("Assignment 1 - Your Books Everywhere - Login");
		setSize(new Dimension(730, 450));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		add(panel);
	}

	public void initializeComponents() {
		createAccountButton.setBounds(300, 125, 150, 50);
		loginButton.setBounds(300, 225, 150, 50);
//		depuneButton.setBounds(300, 50, 150, 50);
//		retrageButton.setBounds(300, 125, 150, 50);
//		titularLabel.setBounds(300, 200, 75, 30);
//		titularText.setBounds(375, 200, 75, 30);
//		contLabel.setBounds(300, 250, 75, 30);
//		contText.setBounds(375, 250, 75, 30);
//		sumaLabel.setBounds(300, 300, 75, 30);
//		sumaText.setBounds(375, 300, 75, 30);
//		titularLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		contLabel.setHorizontalAlignment(SwingConstants.RIGHT);
//		sumaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public void addComponents() {
		panel.setLayout(null);
//		panel.add(createAccountButton);
//		panel.add(loginButton);
//		panel.add(retrageButton);
//		panel.add(depuneButton);
//		panel.add(sumaLabel);
//		panel.add(titularLabel);
//		panel.add(contLabel);
//		panel.add(sumaText);
//		panel.add(titularText);
//		panel.add(contText);
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
				loginGUI = new LoginGUI();
				loginGUI.setVisible(true);

			}
		});
	}

//		depuneButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				try {
////					int idT = Integer.parseInt(titularText.getText());
////					int idC = Integer.parseInt(contText.getText());
////					int sold = Integer.parseInt(sumaText.getText());
////					Bank banca = new Bank();
////					Person titular = new Person(idT, null, null, null);
////					Account cont = new Account(idC, 0, 0, null);
////					banca.addMoney(titular, cont, sold);
////				} catch (ClassNotFoundException e1) {
////					e1.printStackTrace();
////				} catch (IOException e1) {
////					e1.printStackTrace();
////				}
//			}
//		});
//
//		retrageButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
////				try {
////					int idT = Integer.parseInt(titularText.getText());
////					int idC = Integer.parseInt(contText.getText());
////					int sold = Integer.parseInt(sumaText.getText());
////					Bank banca = new Bank();
////					Person titular = new Person(idT, null, null, null);
////					Account cont = new Account(idC, 0, 0, null);
////					banca.withdrawMoney(titular, cont, sold);
////				} catch (ClassNotFoundException e1) {
////					e1.printStackTrace();
////				} catch (IOException e1) {
////					e1.printStackTrace();
////				}
//			}
//		});


}
