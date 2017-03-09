import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
import javax.servlet.RequestDispatcher;
public class DeleteProduct extends HttpServlet
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
		//String username="puneeth";
		HttpSession session = request.getSession();
		String product=request.getParameter("dname");
		
		out.print("<div id=\"content\">");
		session.setAttribute("username",username);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			System.out.println("Hello "+username+"product "+product);
			stmt.executeUpdate("DELETE FROM cart WHERE username='"+username+"' and id='"+product+"'");
			System.out.println("Hello");
			RequestDispatcher dispatcher = request.getRequestDispatcher("cart1");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println(e);
			}
			}
	}

