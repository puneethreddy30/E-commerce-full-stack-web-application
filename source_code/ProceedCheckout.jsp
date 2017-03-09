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
try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			
			ResultSet rs=stmt.executeQuery("select sum(productprice) as total from cart where username='"+username+"'");
			out.print("<div id=\"content\"><form action=\"Confirm.jsp\"><table border=\"1px\"><th align=\"center\">Detailed Info about orders and confirmation</th>");
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
			out.print("<a href='Cart1.jsp'>Review your products</a><br><a href='proceed'>Place your Order</a></div>");
		}
		catch(Exception e)
		{
			System.out.println(e);
			}
%>
<%@ include file="site_footer.jsp" %>