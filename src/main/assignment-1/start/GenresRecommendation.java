package start;

import java.sql.SQLException;
import java.util.ArrayList;

import bll.BookBLL;
import model.Book;

public class GenresRecommendation implements Recommendation {

	public ArrayList<Book> giveRecommendation() throws SQLException {
		ArrayList<Book> array = new ArrayList<Book>();
		ArrayList<Book> genre = new ArrayList<Book>();
		BookBLL bookBll = new BookBLL();
		genre = bookBll.getBooks();
		Book aux = genre.get(0);
		array.add(aux);
		boolean contains = false;
		for(Book book: genre) {
			for(Book auxBook: array) {
				if(book.getGenre().equals(auxBook.getGenre())) {
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
