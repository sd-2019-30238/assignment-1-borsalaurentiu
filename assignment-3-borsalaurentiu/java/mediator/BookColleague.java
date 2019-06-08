package mediator;

import model.Book;

public class BookColleague implements Colleague {
	private Book book;
	private String type;

	public BookColleague(Book book) {
		this.book = book;
		this.type = "addBook";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public String getType() {
		return type;
	} 

	public void setType(String type) {
		this.type = type;
	}

}
