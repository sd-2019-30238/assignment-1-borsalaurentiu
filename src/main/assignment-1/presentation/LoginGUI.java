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

import model.User;

public class LoginGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	public LoginGUI(User user) {
		initializeFrame(user);
		addComponents();
		initializeComponents();
		addListeners();
	}

	public void initializeFrame(User user) {
		setTitle("Assignment 1 - Your Books Everywhere - User: " + user.getName());
		setSize(new Dimension(730, 650));
		setLocationRelativeTo(null);
		setLocation(0, 0);
		setResizable(false);
		setVisible(true);
		add(panel);
	}

	public void initializeComponents() {
	}

	public void addComponents() {
		panel.setLayout(null);
	}

	public void addListeners() {
	}

}
