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
public class RegisterToDB extends HttpServlet
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
		String cpassword=request.getParameter("cpass_word");
		if(password.equals(cpassword))
		{
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select username from user where username='"+username+"'");
				if(rs.next())
				{
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Username already exists');");  
					out.println("window.location.href = 'Register';");
					out.println("</script>");
				}
				else
				{
					stmt.executeUpdate("insert into user values('"+username+"','"+password+"')");
					con.close();
					stmt.close();
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Your Account has been created. Click Ok to continue.');");  
					out.println("window.location.href = 'Login';");
					out.println("</script>");
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else
		{
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Password and confirm password does not match.');");  
			out.println("window.location.href = 'Register';");
			out.println("</script>");
		}
	}
	
}

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
	
public class Cart1 extends HttpServlet
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
		
		out.print("<div id=\"content\">");
		try
		{
		if (session.getAttribute("username")!=null)
			{
			session.setAttribute("username",username);
		
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			
			ResultSet rs=stmt.executeQuery("select * from cart where username='"+username+"'");
			out.print("<table border=\"1px\"><th>Name</th><th>Price</th><th>Action</th>");
			
			while(rs.next())
			{
				String pname=rs.getString("productname");
				int pprice=rs.getInt("productprice");
				int id=rs.getInt("id");
				out.print("<tr><td>");
				out.println(pname);
				out.print("</td><td>");
				out.println("$"+pprice);
				out.print("</td><td>");
				out.println("<form action=\"Delete\"><input type=\"hidden\" value="+id+" name=\"dname\"><input type=\"submit\" value=\"Delete\"></form>");
				out.print("</td></tr>");
			}
			out.print("</table>");
			out.print("<a href='proceed'>Proceed to Checkout</a>");
		}
		else
			{
		out.print("Your Cart is empty");
		
			}
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

public class ProceedCheckout extends HttpServlet
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
		
		out.print("<div id=\"content\">");
		if (session.getAttribute("username")!=null)
			{
			session.setAttribute("username",username);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			
			ResultSet rs=stmt.executeQuery("select sum(productprice) as total from cart where username='"+username+"'");
			out.print("<form action=\"confirm\"><table border=\"1px\"><th align=\"center\">Detailed Info about orders and confirmation</th>");
			while(rs.next())
			{
				int total=rs.getInt("total");
				out.print("<tr><td>");
				out.print("Total");
				out.print("</td><td>");
				out.println("<b>$"+total+"</b>");
				out.print("</td></tr>");
				out.print("</tr><tr><td><input type=\"hidden\" name=\"total\" value="+total+">User Address</td><td><input type=\"text\" name=\"address\">");
			}
			
			out.print("</td></tr><tr><td>Credit Card</td><td><input type=\"text\" name=\"creditcard\"></td></tr>");
			out.print("<tr><td></td><td><input type=\"submit\" value=\"Place Your Order\"></td></tr></table></form>");
			out.print("<a href='cart1'>Review your products</a><br><a href='proceed'>Place your Order</a>");
		}
		catch(Exception e)
		{
			System.out.println(e);
			}
			}
			else
			{
		out.print("Your Cart is empty");
		
			}
			out.print("</div>");
		All.includeHtml("site_sidebar.html");
		All.includeHtml("site_footer.html");
	
	
	}
}