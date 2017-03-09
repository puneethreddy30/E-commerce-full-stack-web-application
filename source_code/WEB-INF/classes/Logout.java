
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import javax.servlet.*;  
import javax.servlet.http.*;  

@WebServlet(name = "Logout", urlPatterns = "/Logout")
public class Logout extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ForLogout userLogout = new ForLogout(request, response);
		userLogout.logout();
		response.sendRedirect("Home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ForLogout ForLogout = new ForLogout(request, response);
		ForLogout.logout();
		response.sendRedirect("Home");
	}

}

class ForLogout {
	HttpServletRequest req;
	PrintWriter out;
	String url;
	HttpSession session;

	public ForLogout() {};

	public ForLogout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		this.req = req;
		this.out = res.getWriter();
		this.session = req.getSession(true);
	}

	public ForLogout(HttpServletRequest req, PrintWriter out) {
		this.req = req;
		this.out = out;
		this.session = req.getSession(true);
	}
		public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}

}