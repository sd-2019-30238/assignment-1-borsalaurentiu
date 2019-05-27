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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bll.BookBLL;
import bll.PaymentBLL;
import bll.UserBLL;
import model.Book;
import model.Payment;
import model.User;

public class LoginGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JPanel panel = new JPanel();
	private JTable table = new JTable();
	private JTable notTable = new JTable();
	private JScrollPane tablePane = new JScrollPane(table);
	private JScrollPane notTablePane = new JScrollPane(notTable);

	private JButton borrowButton = new JButton("Borrow!");	
	private JButton returnButton = new JButton("Return!");	
	private JLabel nameLabel = new JLabel("Title: ");
	private JTextField nameText = new JTextField();

	public LoginGUI(User user) throws SQLException {
		initializeFrame(user);
		addComponents();
		initializeComponents();
		addListeners(user);
		showTable();
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
		tablePane.setBounds(0, 0, 480, 200);
		notTablePane.setBounds(0, 300, 480, 200);

		table.setBounds(0, 0, 480, 200);
		notTable.setBounds(0, 300, 480, 200);

		borrowButton.setBounds(500, 200, 210, 50);
		returnButton.setBounds(500, 250, 210, 50);
		nameLabel.setBounds(500, 150, 100, 30);
		nameText.setBounds(600, 150, 110, 30);
		nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	}

	public void addComponents() {
		panel.setLayout(null);
		panel.add(table);
		panel.add(notTable);
		panel.add(tablePane);
		panel.add(notTablePane);
		panel.add(nameLabel);
		panel.add(nameText);
		panel.add(borrowButton);
		panel.add(returnButton);

	}

	public void addListeners(User user) {
		PaymentBLL paymentBll = new PaymentBLL();
		final String name = user.getName();
		if(paymentBll.isVerified(name).equals("yes")) {
			
			borrowButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String title = nameText.getText();
					BookBLL bookBll = new BookBLL();
					bookBll.updateBorrow(name, title);
					try {
						showTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			returnButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String title = nameText.getText();
					BookBLL bookBll = new BookBLL();
					bookBll.updateReturn(name, title);
					try {
						showTable();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});
			
			
		} else {

		}
	}

	public void showTable() throws SQLException  {
		BookBLL bookBll = new BookBLL();
		ArrayList<Book> books = new ArrayList<Book>();
		books = bookBll.getBooks();

		String[] coloane = {"Title","Author","Genre","Release date"};		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableModel notModel = new DefaultTableModel();

		model.addColumn("title");
		model.addColumn("author");
		model.addColumn("genre");
		model.addColumn("releaseDate");
		model.addRow(coloane);	

		notModel.addColumn("title");
		notModel.addColumn("author");
		notModel.addColumn("genre");
		notModel.addColumn("releaseDate");
		notModel.addRow(coloane);	

		for(Book book: books){
			Object[] row = new Object[4];
			row[0] = book.getTitle();
			row[1] = book.getAuthor(); 
			row[2] = book.getGenre();
			row[3] = book.getReleaseDate();
			if(book.isAvailable() == 1) {
				model.addRow(row);
			} else {
				notModel.addRow(row);
			}
		}
		table.setModel(model);
		notTable.setModel(notModel);
	}


}
