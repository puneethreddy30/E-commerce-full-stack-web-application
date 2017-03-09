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
			out.print("<div id=\"content\"><form action=\"Tracking.jsp\"><table><tr><td>Enter your Order id :</td><td><input type=\"text\" name=\"orderid\"></td></tr>");
			out.print("<tr><td></td><td><input type=\"submit\" value=\"Track id\"></td></tr></table></form></div>");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
			}

%>
<%@ include file="site_footer.jsp" %>