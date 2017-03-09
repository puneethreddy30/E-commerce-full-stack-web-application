import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
public class ViewProduct extends HttpServlet
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
			
			ResultSet rs=stmt.executeQuery("select * from products");
			out.print("<table border=\"1px\"><th>Name</th><th>Price</th><th>Action</th>");
			
			while(rs.next())
			{
				String pname=rs.getString("productname");
				String pprice=rs.getString("productprice");
				String productfrom=rs.getString("producttype");
				String imagelink=rs.getString("imagelink");
				out.print("<tr><td>");
				out.println(pname);
				out.print("</td><td>");
				out.println("$"+pprice);
				out.print("</td><td>");
				out.println("<img src="+imagelink+" height=\"100px\" width=\"150px\">");
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