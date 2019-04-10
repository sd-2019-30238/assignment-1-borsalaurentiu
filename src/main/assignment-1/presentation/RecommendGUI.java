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
import start.Recommendation;
import start.RecommendationFactory;

public class RecommendGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();

	public RecommendGUI() {
		factoryDesignPattern();
		initializeFrame();
		addComponents();
		initializeComponents();
		addListeners();
	}

	public void initializeFrame() {
		setTitle("Assignment 1 - Your Books Everywhere - Recommendation");
		setSize(new Dimension(635, 650));
		setLocationRelativeTo(null);
		setLocation(730, 0);
		setResizable(false);
		setVisible(true);
		add(panel);
	}

	public void factoryDesignPattern() {
		RecommendationFactory shapeFactory = new RecommendationFactory();
		Recommendation genres = shapeFactory.getShape("GENRES");
		Recommendation trends = shapeFactory.getShape("TRENDS");
		Recommendation release = shapeFactory.getShape("RELEASE");

		genres.giveRecommendation();
		trends.giveRecommendation();
		release.giveRecommendation();
	}

	public void initializeComponents() {
				
	}


	public void addComponents() {
		panel.setLayout(null);
	}

	public void addListeners() {
	}

}
