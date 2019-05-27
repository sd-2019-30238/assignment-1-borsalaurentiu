package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Book;
public class BookDAO {

	protected static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString = "INSERT INTO book (title, author, genre, releaseDate, available, borrows) VALUES (?,?,?,?,1,0)";
	private static final String insertNameStatementString = "INSERT INTO waiting (title, name, position) VALUES (?,?,0)";
	private static final String returnNameStatementString = "UPDATE waiting SET position = -1 WHERE title = ? AND name = ? AND position = 0";
	private final static String checkStatementString = "SELECT position FROM waiting WHERE title = ? AND name = ?";
	private final static String findStatementString = "SELECT title, author, genre, releaseDate, available FROM book ORDER BY borrows DESC";
	private final static String updateBorrowStatementString = "UPDATE book SET available = 0, borrows = borrows + 1 WHERE title = ?";	
	private final static String updateReturnStatementString = "UPDATE book SET available = 1 WHERE title = ?";	

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

	public static void updateBorrow(String name, String title) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		PreparedStatement insertStatement = null;
		try {

			insertStatement = dbConnection.prepareStatement(insertNameStatementString);
			insertStatement.setString(1, title);
			insertStatement.setString(2, name);
			insertStatement.executeUpdate();

			updateStatement = dbConnection.prepareStatement(updateBorrowStatementString);
			updateStatement.setString(1, title);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"BookDAO:updateStatus " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}	
	}

	public static void updateReturn(String name, String title) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		PreparedStatement insertStatement = null;
		PreparedStatement checkStatement = null;
		ResultSet rs = null;
		try {
			checkStatement = dbConnection.prepareStatement(checkStatementString);
			checkStatement.setString(1, title);
			checkStatement.setString(2, name);
			rs = checkStatement.executeQuery();
			rs.next();
			if(rs.getInt("position") == 0){
				updateStatement = dbConnection.prepareStatement(updateReturnStatementString);
				updateStatement.setString(1, title);
				updateStatement.executeUpdate();
			}
			insertStatement = dbConnection.prepareStatement(returnNameStatementString);
			insertStatement.setString(1, title);
			insertStatement.setString(2, name);
			insertStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"BookDAO:updateStatus " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(checkStatement);
			ConnectionFactory.close(insertStatement);
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