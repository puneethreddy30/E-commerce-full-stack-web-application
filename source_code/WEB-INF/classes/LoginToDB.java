import db.DBHelper;;
import java.io.IOException;
import java.io.*;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
public class LoginToDB extends HttpServlet
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/bestdeals";
	final String USER = "puneethreddy30";
	final String PASS = "puneethreddy";
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("user_name");
		String password=request.getParameter("pass_word");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");

		HashMap<String, User> hm = new HashMap<String, User>();
		if (usertype.equals("customer")) {
			hm.putAll(UserHashMap.customer);
		} else if (usertype.equals("retailer")) {
			hm.putAll(UserHashMap.retailer);
		} else if (usertype.equals("manager")) {
			hm.putAll(UserHashMap.manager);
		}
		if(username.equals("salesadmin")&&password.equals("salesadmin"))
			response.sendRedirect("sales");
		else if(username.equals("storeadmin")&&password.equals("storeadmin"))
			response.sendRedirect("store");	
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select username,password from user where username='"+username+"' and password='"+password+"'");
				if(rs.next())
				{
				String db_user=rs.getString("username");	
				String db_pass=rs.getString("password");
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
				dispatcher.forward(request, response);
				}
				else
				{
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('The details you have entered is incorrect. Please try again.');");  
				out.println("window.location.href = 'Login';");
				out.println("</script>");
				}
				
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
	}
	
