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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.Book;
import model.User;
import start.Recommendation;
import start.RecommendationFactory;

public class RecommendGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JLabel topTrendsLabel = new JLabel("Top 3 most borrowed books by trends!");
	private JLabel topGenresLabel = new JLabel("Top 3 most borrowed books by genres!");
	private JLabel topReleaseDateLabel = new JLabel("Top 3 most borrowed books by release date!");

	public RecommendGUI() throws SQLException {
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

	public void factoryDesignPattern() throws SQLException {
		int x = 20, y = 100;
		RecommendationFactory shapeFactory = new RecommendationFactory();
		Recommendation genres = shapeFactory.getShape("GENRES");
		Recommendation trends = shapeFactory.getShape("TRENDS");
		Recommendation release = shapeFactory.getShape("RELEASE");
		ArrayList<Book> genresArray = new ArrayList<Book>();
		ArrayList<Book> trendsArray = new ArrayList<Book>();
		ArrayList<Book> releaseArray = new ArrayList<Book>();

		genresArray = genres.giveRecommendation();
		trendsArray = trends.giveRecommendation();
		releaseArray = release.giveRecommendation();
		int index = 0;
		for(Book genre: genresArray) {
			if(index < 3) {
				JLabel genreLabel = new JLabel(genre.toString());
				panel.add(genreLabel);
				genreLabel.setBounds(x, y, 200, 80);
				genreLabel.setHorizontalAlignment(SwingConstants.CENTER);
				x += 200; 
				index++;
			}
			else {
				index = 0;
				break;
			}
		}
		x = 20;
		y += 175;
		for(Book trend: trendsArray) {
			if(index < 3) {
				JLabel trendLabel = new JLabel(trend.toString());
				panel.add(trendLabel);
				trendLabel.setBounds(x, y, 200, 80);
				trendLabel.setHorizontalAlignment(SwingConstants.CENTER);
				x += 200;  
				index++;
			}
			else {
				index = 0;
				break;
			}
		}
		x = 20;
		y += 175;
		for(Book date: releaseArray) {
			if(index < 3) {
				JLabel releaseLabel = new JLabel(date.toString());
				panel.add(releaseLabel);
				releaseLabel.setBounds(x, y, 200, 80);
				releaseLabel.setHorizontalAlignment(SwingConstants.CENTER);
				x += 200;  
				index++;
			}
			else {
				index = 0;
				break;
			}
		}
	}

	public void initializeComponents() {
		topGenresLabel.setBounds(0, 60, 630, 30);
		topGenresLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topTrendsLabel.setBounds(0, 235, 630, 30);
		topTrendsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		topReleaseDateLabel.setBounds(0, 410, 630, 30);
		topReleaseDateLabel.setHorizontalAlignment(SwingConstants.CENTER);

	}


	public void addComponents() {
		panel.setLayout(null);
		panel.add(topTrendsLabel);
		panel.add(topGenresLabel);
		panel.add(topReleaseDateLabel);
	}

	public void addListeners() {
	}

}
