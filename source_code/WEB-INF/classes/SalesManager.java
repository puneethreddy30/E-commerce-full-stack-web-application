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
public class SalesManager extends HttpServlet
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/bestdeals";
	final String USER = "puneethreddy30";
	final String PASS = "puneethreddy";
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException
	{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		All All = new All(request,out);
		All.includeHtml("store_header.html");
		out.print("<div id=\"content\">");
		try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select username from user");
				out.print("<table broder=\"1px\"><th>Username(Click on user to place an order under user)</th>");
				while(rs.next())
				{
					String user=rs.getString("username");
					out.print("<tr><td><a href=\"All\">"+user+"</a></td><td><form action=\"remove\"><input type=\"hidden\" name=\"duser\" value="+user+"><input type=\"submit\" value=\"Delete User\"></td></tr>");
					
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
	}
}