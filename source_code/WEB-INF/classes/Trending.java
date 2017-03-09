import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

public class Trending extends HttpServlet 
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
		int i=1;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		//HttpSession session=session.getSession(true);
		
		PrintWriter out = response.getWriter();
		All All = new All(request,out);
		//String username=All.forSession();
		//String username="puneeth";
		HttpSession session = request.getSession();
		All.includeHtml("site_header.html");
		out.print("<section id=\"content\">");
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
		Statement stmt=con.createStatement();
		ResultSet rs=stmt.executeQuery("select distinct productname,reviewrating from review order by reviewrating desc");
		out.print("<table border=\"1px\">");
		out.print("Top 5 products based on review rating<br><th>Product Name</th><th>Product Rating</th>");
		while(rs.next())
		{
			String pname=rs.getString("productname");
			int review=rs.getInt("reviewrating");
			out.print("<tr><td>"+pname+"</td>");
			out.print("<td>"+review+"</td></tr>");
			
		}
		out.print("</table>");
		Connection con1=DriverManager.getConnection(DB_URL,USER,PASS);
		Statement stmt1=con.createStatement();
		ResultSet rs1=stmt1.executeQuery("select distinct retailercity,count(productname) as c  from review group by productname");
		out.print("<table border=\"1px\">");
		out.print("Top five zip-codes where maximum number of products sold<br><th>City Name</th><th>Number of Products sold</th>");
		while(rs1.next())
		{
			String retailerzip=rs1.getString("retailercity");
			int c=rs1.getInt("c");
			out.print("<tr><td>"+retailerzip+"</td>");
			out.print("<td>"+c+"</td></tr>");
		}
		out.print("</table>");
		Connection con2=DriverManager.getConnection(DB_URL,USER,PASS);
		Statement stmt2=con2.createStatement();
		ResultSet rs2=stmt2.executeQuery("select distinct productname,count(productname) as c  from review group by productname");
		out.print("<table border=\"1px\">");
		out.print("Top most sold products<br><th>Product Name</th><th>Number of Products sold</th>");
		while(rs2.next())
		{
			String retailerzip=rs2.getString("productname");
			int c=rs2..getInt("c");
			out.print("<tr><td>"+retailerzip+"</td>");
			out.print("<td>"+c+"</td></tr>");
		}
		out.print("</table></section>");
		}
		catch(Exception e)
		{
			out.print(e);
		}
		All.includeHtml("site_sidebar.html");
		All.includeHtml("site_footer.html");
	}


}

