import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
public class Tracking extends HttpServlet
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
		HttpSession session = request.getSession();
		
		out.print("<div id=\"content\">");
		
		session.setAttribute("username",username);
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			String trackid=request.getParameter("orderid");
			ResultSet rs=stmt.executeQuery("select price,date,address,creditcard from orders where username='"+username+"' and id='"+trackid+"'");
			
			if(rs.next())
				{
					out.print("<table border=\"1px\"><th>Date of Order</th><th>Delivery Date</th><th>Total Payable Amount</th><th>Address</th><th>Credit Card Number</th>");
					String total=rs.getString("price");
					String date=rs.getString("date");
					String address=rs.getString("address");
					String credit=rs.getString("creditcard");	
					
					out.print("<tr><td>"+date+"</td><td>12/05/2016</td><td>$"+total+"</td><td>"+address+"</td><td>"+credit+"</td></tr>");
						out.print("</table>");
				}
				else
					out.print("<br><br><center><b>Sorry. Tracking id "+trackid+" does not match with your order.</b></center>");
				
			
			
				
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
			
	
	
	
