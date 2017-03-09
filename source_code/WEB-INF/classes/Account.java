import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
public class Account extends HttpServlet
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
		session.setAttribute("username",username);
		out.print("<div id=\"content\">");
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select username,password from user where username='"+username+"'");
				out.print("<table broder=\"1px\"><th align=\"center\">Your Details</th>");
				while(rs.next())
				{
				String db_user=rs.getString("username");	
				String db_pass=rs.getString("password");
				out.print("<tr><td>");
				out.print("Your Username");
				out.print("</td><td>");
				out.print(db_user);
				out.print("</td></tr><tr><td>");
				out.print("Your Password");
				out.print("</td><td>");
				out.print(db_pass);
				out.print("</td></tr><tr><td>");
				out.print("Your Account Type");
				out.print("</td><td>");
				out.print("Customer");
				out.print("</td></tr></table>");
				}
				}
				catch(Exception e)
				{
				System.out.print(e);
				}
			out.print("</div>");
		All.includeHtml("site_sidebar.html");
		All.includeHtml("site_footer.html");
	
	
	}
}