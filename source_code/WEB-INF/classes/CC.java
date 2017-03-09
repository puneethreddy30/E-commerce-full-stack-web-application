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
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class CC extends HttpServlet
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/bestdeals";
	final String USER = "puneethreddy30";
	final String PASS = "puneethreddy";
public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
			PrintWriter out = response.getWriter();
			All All = new All(request,out);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					Date date = new Date();
			String idate=(dateFormat.format(date)).toString();
			
			String username1=All.forSession();
			
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
		Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			ResultSet rs=stmt.executeQuery("select productname,productprice from cart where username='"+username1+"'");
			while(rs.next())
			{
			String productname1=rs.getString("productprice");
			String price1=rs.getString("productname");
			stmt.executeUpdate("insert into cart1 values('"+idate+"','"+username1+"','"+productname1+"','"+price1+"')");
			out.print("Your order has been placed");
			}
			}
			catch(Exception e)
			{
				out.print(e);
			}
	}
}