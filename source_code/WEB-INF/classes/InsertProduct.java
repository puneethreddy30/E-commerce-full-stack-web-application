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
public class InsertProduct extends HttpServlet
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

try
		{
			String price=request.getParameter("name");
			String pname=request.getParameter("price");
			String ptype=request.getParameter("type");
			String plink=request.getParameter("link");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			int i=stmt.executeUpdate("insert into products values('"+price+"','"+pname+"','"+ptype+"','"+plink+"')");
			if(i>0)
			{
				out.println("<script type=\"text/javascript\">");  
			out.println("alert('Product Added');");  
			out.println("window.location.href = 'store';");
			out.println("</script>");
				
			
			}
		}
catch(Exception e)
{
	out.print(e);
}
	}
}