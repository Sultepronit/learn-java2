package pkg;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Login() {
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String n = req.getParameter("userName");
		String p = req.getParameter("userPass");
		System.out.println(p);
		/*if(p.equals("servlet")) {
			RequestDispatcher rd = req.getRequestDispatcher("servlet2");
			rd.forward(req,resp);
		}
		else {
			out.print("Sorry, Username or Password Error!");
			RequestDispatcher rd = req.getRequestDispatcher("/index.html");
			rd.include(req, resp);
		}*/
	}

}
