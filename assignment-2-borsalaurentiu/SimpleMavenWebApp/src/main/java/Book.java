public class Book {
	private int id;
	private String title;
	private String author;
	private String genre;
	private int releaseDate;
	private int available;

	public Book(String title, String author, String genre, int releaseDate, int available) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.releaseDate = releaseDate;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(int releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int isAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "<html>Title : " + title + "<br>Author : " + author + "<br>Genre : " + genre + "<br>ReleaseDate : " + releaseDate
				+ "<br>Available : " + (available == 1 ? " yes" : " no") + "</html>";
	}
	
}
