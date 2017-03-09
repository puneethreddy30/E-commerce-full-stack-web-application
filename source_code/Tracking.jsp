<%@ include file="site_lheader.jsp" %>
<%@ include file="site_sidebar.jsp" %>
<%@page import="java.io.IOException,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
	
<%!
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
		
		
%>
<%
session = request.getSession(true);
String username = session.getAttribute("username").toString();
try{
	Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			String trackid=request.getParameter("orderid");
			ResultSet rs=stmt.executeQuery("select price,date,address,creditcard from orders where username='"+username+"' and id='"+trackid+"'");
			
			if(rs.next())
				{
					out.print("<div id=\"content\"><table border=\"1px\"><th>Date of Order</th><th>Total Payable Amount</th><th>Address</th><th>Credit Card Number</th>");
					String total=rs.getString("price");
					String date=rs.getString("date");
					String address=rs.getString("address");
					String credit=rs.getString("creditcard");	
					
					out.print("<tr><td>"+date+"</td><td>$"+total+"</td><td>"+address+"</td><td>"+credit+"</td></tr></div>");
						out.print("</table>");
				}
				else
					out.print("<br><br><center><b>Sorry. Tracking id "+trackid+" does not match with your order.</b></center></div>");
				
			
}		
				
		
		catch(Exception e)
		{
			System.out.println(e);
			}
%>
<%@ include file="site_footer.jsp" %>