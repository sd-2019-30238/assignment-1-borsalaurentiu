import java.io.IOException;  
import java.io.PrintWriter;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import cqrs.BookRead;

public class BorrowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static String username, title;

	public void readData(HttpServletRequest request) {
		username = request.getParameter("name");  
		title = request.getParameter("title");  
	}
	
	public void borrow(HttpServletRequest request, HttpServletResponse response, PrintWriter out) 
			throws ServletException, IOException {
		BookRead.setBorrow(title);	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		readData(request);
		borrow(request, response, out);
		out.close();  
	}

}
