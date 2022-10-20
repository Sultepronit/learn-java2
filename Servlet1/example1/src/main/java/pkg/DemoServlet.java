package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class DemoServlet extends HttpServlet {
	
	public DemoServlet() {
	}
	
	public void doGet(HttpRequest req, HttpResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();//get the stream to write the data
		
		pw.println("<html><body>");
		pw.println("Welcome to servlet");
		pw.println("</body></html>");
		
		pw.close();//closing the stream
	}
}