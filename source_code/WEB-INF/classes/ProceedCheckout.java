import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
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