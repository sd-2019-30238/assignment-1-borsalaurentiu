package decorator;

public abstract class BookDecorator implements IBook {
	private IBook iBook;

	public BookDecorator(IBook iBook) {
		this.iBook = iBook;
	}

	public void setOwnership(String book) {
		iBook.setOwnership(book);
	}

	public IBook getIBook() {
		return iBook;
	}

	public void setIBook(IBook iBook) {
		this.iBook = iBook;
	}
}