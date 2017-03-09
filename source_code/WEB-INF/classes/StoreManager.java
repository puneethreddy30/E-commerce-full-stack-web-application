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
public class StoreManager extends HttpServlet
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
		All.includeHtml("sales_header.html");
		out.print("<section id=\"content\"><!--<b>Product Added !</b>--><h2>Add Product Here</h2><form action=\"Insert\"><table><tr><td>Name of product</td><td><input name=\"name\" type=\"text\"></td></tr>");
		out.print("<tr><td>Price of product</td><td><input name=\"price\" type=\"text\"></td></tr>");
		out.print("<tr><td>Type of Product</td><td><input name=\"type\" type=\"text\"></td></tr>");
		out.print("<tr><td>Image Link</td><td><input name=\"link\" type=\"text\"></td></tr><tr><td></td><td><input type=\"submit\"></td></tr></form></table>");
	}
}