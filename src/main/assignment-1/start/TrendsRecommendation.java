package start;

import java.sql.SQLException;
import java.util.ArrayList;

import bll.BookBLL;
import model.Book;

public class TrendsRecommendation implements Recommendation {	
	
	public ArrayList<Book> giveRecommendation() throws SQLException {
		ArrayList<Book> array = new ArrayList<Book>();
		BookBLL bookBll = new BookBLL();
		array = bookBll.getBooks();
		return array;
	}
}
