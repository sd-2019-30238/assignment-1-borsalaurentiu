package start;

import java.sql.SQLException;
import java.util.ArrayList;

import model.Book;

public interface Recommendation {
	ArrayList<Book> giveRecommendation() throws SQLException;
}
