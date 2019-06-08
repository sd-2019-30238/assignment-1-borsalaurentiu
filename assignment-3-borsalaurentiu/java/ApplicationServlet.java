import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import cqrs.BookRead;
import model.Book;

public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void showTable(HttpServletRequest request, HttpServletResponse response, PrintWriter out) 
			throws SQLException{
		ArrayList<Book> books = new ArrayList<Book>();
		books = BookRead.getBooks();
		out.print("<html><center>Available books"
				+ "<table border = \"1\">"
				+ "<tr>"
				+ "<th>Title</th><th>Author</th><th>Genre</th><th>Release date</th>"
				+ "</tr>");

		for(Book book: books){
			if(book.isAvailable() == 1)
				out.print("<tr>"
						+ "<td>" + book.getTitle()
						+ "</td>"

				+ "<td>" + book.getAuthor()
				+ "</td>"

				+ "<td>" + book.getGenre()
				+ "</td>"

				+ "<td>" + book.getReleaseDate()
				+ "</td>"

				+ "</tr>");
		}
		out.print("</table></center></html>");

		out.print("<html><center><br>Unavailable books</html>");
		out.print("<html><center>"
				+ "<table border = \"1\">"
				+ "<tr>"
				+ "<th>Title</th><th>Author</th><th>Genre</th><th>Release date</th>"
				+ "</tr>");

		for(Book book: books){
			if(book.isAvailable() != 1)
				out.print("<tr>"
						+ "<td>" + book.getTitle()
						+ "</td>"

				+ "<td>" + book.getAuthor()
				+ "</td>"

				+ "<td>" + book.getGenre()
				+ "</td>"

				+ "<td>" + book.getReleaseDate()
				+ "</td>"

				+ "</tr>");
		}
		out.print("</table></center></html>");

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		doSomething(out);
		try {
			showTable(request, response, out);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.close();  
	}

	private void doSomething(PrintWriter out) {
		String borrowString = "<a href = \"./borrow.jsp\" target=\"popup\" onclick=\"window.open('./borrow.jsp','name','width=600,height=400')\"";
		String returnString = "<a href = \"./return.jsp\" target=\"popup\" onclick=\"window.open('./return.jsp','name','width=600,height=400')\"";
		out.print("<html><center><br> "+ borrowString + "> Borrow a book! </a>"
				+ " or " + returnString + "> Return a book! </a><br><br></center></html>");
	}
}
