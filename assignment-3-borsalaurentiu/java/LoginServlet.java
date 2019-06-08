import java.io.IOException;  
import java.io.PrintWriter;  

import javax.servlet.RequestDispatcher;  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

import cqrs.UserRead;

public class LoginServlet extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	static String username, password;

	public void readData(HttpServletRequest request) {
		username = request.getParameter("name");  
		password = request.getParameter("password");  
	}

	public void login(HttpServletRequest request, HttpServletResponse response, PrintWriter out) 
			throws ServletException, IOException {

		if(UserRead.validate(username, password)){  
			RequestDispatcher rd = request.getRequestDispatcher("ApplicationServlet");  
			rd.forward(request, response);  
		}  
		else{  
			out.print("<html><center>Sorry username or password error!</center></html>");  
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");  
			rd.include(request, response);  
		}  
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		readData(request);
		login(request, response, out);

		out.close();  
	}  
} 