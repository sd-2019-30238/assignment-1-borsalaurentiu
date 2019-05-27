package start;

import java.sql.SQLException;
import java.util.ArrayList;

import bll.BookBLL;
import model.Book;

public class ReleaseRecommendation implements Recommendation {
	
	public ArrayList<Book> giveRecommendation() throws SQLException {
		ArrayList<Book> array = new ArrayList<Book>();
		ArrayList<Book> release = new ArrayList<Book>();
		BookBLL bookBll = new BookBLL();
		release = bookBll.getBooks();
		Book aux = release.get(0);
		array.add(aux);
		boolean contains = false;
		for(Book book: release) {
			for(Book auxBook: array) {
				if(book.getReleaseDate() == auxBook.getReleaseDate()) {
					contains = true;
				}
			}
			if(contains == false) {
				array.add(book);
			}
			contains = false;
		}
		return array;
	}
}