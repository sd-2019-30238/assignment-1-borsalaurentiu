package cqrs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Book;

public class BookRead {
	protected static final Logger LOGGER = Logger.getLogger(BookRead.class.getName());
	private final static String findStatementString = "SELECT title, author, genre, releaseDate, available FROM book ORDER BY borrows DESC";
	private final static String updateBorrowStatementString = "UPDATE book SET available = 0, borrows = borrows + 1 WHERE title = ?";	
	private final static String updateReturnStatementString = "UPDATE book SET available = 1 WHERE title = ?";	
	
	public static void setBorrow(String title) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateBorrowStatementString);
			updateStatement.setString(1, title);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"BookDAO:updateStatus " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}	
	}	
	
	public static void setAvailable(String title) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		try {
			updateStatement = dbConnection.prepareStatement(updateReturnStatementString);
			updateStatement.setString(1, title);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"BookDAO:updateStatus " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}	
	}
			
	public static ArrayList<Book> getBooks() throws SQLException{
		ArrayList<Book> books = new ArrayList<Book>();
		Connection dbConnection = ConnectionFactory.getConnection();

		ResultSet rs = null;
		PreparedStatement findStatement = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			rs = findStatement.executeQuery();

			while(rs.next()) {
				Book book = new Book(rs.getString("title"), rs.getString("author"), rs.getString("genre"), Integer.parseInt(rs.getString("releaseDate")), Integer.parseInt(rs.getString("available")));
				books.add(book);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "BookDAO:getBooks " + e.getMessage());
		} finally {
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return books;
	}	
}
