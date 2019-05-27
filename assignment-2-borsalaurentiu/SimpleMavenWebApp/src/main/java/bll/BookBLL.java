package bll;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.BookDAO;
import dao.PaymentDAO;
import model.Book;

public class BookBLL {

	public ArrayList<Book> getBooks() throws SQLException{
		ArrayList<Book> books = new ArrayList<Book>();
		books = BookDAO.getBooks();
		return books;
	}
	public void updateBorrow(String name, String title) {
		BookDAO.updateBorrow(name, title);
	}	
	public void updateReturn(String name, String title) {
		BookDAO.updateReturn(name, title);
	}	
}
