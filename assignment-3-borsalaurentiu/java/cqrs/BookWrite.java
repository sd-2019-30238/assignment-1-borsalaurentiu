package cqrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import mediator.BookColleague;
import mediator.BookHandler;
import mediator.Colleague;
import mediator.Handler;
import mediator.Mediator;
import model.Book;

public class BookWrite {
	protected static final Logger LOGGER = Logger.getLogger(UserWrite.class.getName());
	private static final String insertStatementString = "INSERT INTO book (title, author, genre, releaseDate, available, borrows) VALUES (?,?,?,?,1,0)";

	private Mediator mediator;
	
	public BookWrite(Mediator mediator) {
		this.mediator = mediator;
	}

	public Mediator getMediator() {
		return mediator;
	}

	public void setMediator(Mediator mediator) {
		this.mediator = mediator;
	}
	
	public String addBook(Book book)
	{
		Colleague colleague = new BookColleague(book);
		Handler handler = new BookHandler();
		mediator.registerHandler(handler);
		return mediator.handle(colleague);
	}
	
	public static int insert(Book book) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedId = -1;

		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);

			insertStatement.setString(1, book.getTitle());
			insertStatement.setString(2, book.getAuthor());
			insertStatement.setString(3, book.getGenre());
			insertStatement.setInt(4, book.getReleaseDate());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedId = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "BookDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedId;
	}

}
