package model;

public class Book {
	private int id;
	private String title;
	private String author;
	private String genre;
	private String releaseDate;
	private boolean available;
	
	public Book(int id, String title, String author, String genre, String releaseDate, boolean available) {
		this.id = id;
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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
}
