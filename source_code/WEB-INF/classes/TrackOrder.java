import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
public class TrackOrder extends HttpServlet
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/bestdeals";
	//Datase crudentials
	final String USER = "puneethreddy30";
	final String PASS = "puneethreddy";
	HttpSession session; 
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		
		response.setContentType("text/html");
		//HttpSession session=session.getSession(true);
		
		PrintWriter out = response.getWriter();
		All All = new All(request,out);
		All.includeHtml("site_header.html");
		String username=All.forSession();
		HttpSession session = request.getSession();
		
		out.print("<div id=\"content\">");
		
			session.setAttribute("username",username);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			out.print("<form action=\"tracking\"><table><tr><td>Enter your Order id :</td><td><input type=\"text\" name=\"orderid\"></td></tr>");
			out.print("<tr><td></td><td><input type=\"submit\" value=\"Track id\"></td></tr></table></form>");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			}
						out.print("</div>");
		All.includeHtml("site_sidebar.html");
		All.includeHtml("site_footer.html");

			}
			
	
	
	}
