import java.io.IOException;  
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import cqrs.UserWrite;
import model.User;

public class NewAccountServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	static String name, password, paymentPlan, credit;
	
	public void readData(HttpServletRequest request) {
		name = request.getParameter("name");  
		password = request.getParameter("password");  
		paymentPlan = request.getParameter("paymentPlan");  
		credit = request.getParameter("credit");  
	}

	public void create(HttpServletRequest request, HttpServletResponse response, PrintWriter out) 
			throws ServletException, IOException {

		User user = new User(name, password, "user", Integer.parseInt(paymentPlan));
		
		if(UserWrite.insert(user)){  
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp"); 
			rd.forward(request, response);  
		} else {
			out.print("<html><center>This username exists!</center></html>");  
			RequestDispatcher rd = request.getRequestDispatcher("newAccount.jsp");  
			rd.forward(request, response); 
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		readData(request);
		create(request, response, out);
		
		out.close();  
	}  
}