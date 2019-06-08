package decorator;

import cqrs.BookRead;

public class BorrowedBookDecorator extends BookDecorator {

	public BorrowedBookDecorator(IBook iBook) {
		super(iBook);
	}

	@Override
	public void setOwnership(String book) {
		BookRead.setBorrow(book);
	}

}
