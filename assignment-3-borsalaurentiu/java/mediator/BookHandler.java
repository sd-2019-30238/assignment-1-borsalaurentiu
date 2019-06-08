package mediator;

import cqrs.BookWrite;
import model.Book;

public class BookHandler implements Handler {
	private String type;

	public BookHandler() {
		this.setType("addBook");
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String handle(Colleague colleague) {
		Book book = ((BookColleague) colleague).getBook();
		if(BookWrite.insert(book) != -1)
			return "success";
		else
			return "failure";
	}
}
