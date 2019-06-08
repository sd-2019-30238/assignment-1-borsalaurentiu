package decorator;

import cqrs.BookRead;

public class AvailableBookDecorator extends BookDecorator {
	public AvailableBookDecorator(IBook iBook) {
		super(iBook);
	}

	@Override
	public void setOwnership(String book) {
		BookRead.setAvailable(book);
	}
}
