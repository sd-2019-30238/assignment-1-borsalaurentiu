import java.io.IOException;  
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import bll.BookBLL;
import bll.PaymentBLL;
import dao.UserDAO;
import model.Book;
import observer.Subject;

public class ReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String username, title;

	public void readData(HttpServletRequest request) {
		username = request.getParameter("name");  
		title = request.getParameter("title");  
	}
	
	public void returnB(HttpServletRequest request, HttpServletResponse response, PrintWriter out) 
			throws ServletException, IOException {
		BookBLL bookBll = new BookBLL();
		bookBll.updateReturn(username, title);
	}

	public void notifyWaitingList(String title) {
		try {
			Subject.addObserver(title);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		readData(request);
		returnB(request, response, out);
		notifyWaitingList(title);
		out.close();  
	}
}
