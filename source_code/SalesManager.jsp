<%@ include file="store_header.jsp" %>
<%@ include file="site_sidebar.jsp" %>
<%@page import="java.io.IOException,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
	
<%!
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
		int i=0;
		
%>
<%
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
				out.println("</div>");
			}
			
			catch(Exception e)
			{
				System.out.println(e);
			} 
			
			%>
<%@ include file="site_footer.jsp" %>