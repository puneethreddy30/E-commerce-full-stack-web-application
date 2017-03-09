import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class ConfirmOrder extends HttpServlet
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
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
		Date date = new Date();
		String odate=(dateFormat.format(date)).toString();
		
		PrintWriter out = response.getWriter();
		All All = new All(request,out);
		All.includeHtml("site_header.html");
		String username=All.forSession();
		int i=0;
		//String username="puneeth";
		HttpSession session = request.getSession();
		
		out.print("<div id=\"content\">");
		session.setAttribute("username",username);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			String address=request.getParameter("address");
			String credit=request.getParameter("creditcard");
			String total=request.getParameter("total");
			stmt.executeUpdate("DELETE FROM cart WHERE username='"+username+"'");
			int j=stmt.executeUpdate("insert into orders values('"+i+"','"+username+"','"+total+"','"+date+"','"+address+"','"+credit+"')");
			ResultSet rs=stmt.executeQuery("select id from orders where username='"+username+"' and date='"+date+"'");
			if(rs.next())
			{
			int id=rs.getInt("id");
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Order has been place. You can track your order using your order id. Your order id is : "+id+"');");  
			out.println("window.location.href = 'Home';");
			out.println("</script>");
			}
			else
			out.print("Sorry you cannot place the order at this time. Sorry for inconvinience");
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
