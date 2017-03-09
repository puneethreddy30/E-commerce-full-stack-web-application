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
String product=request.getParameter("dname");
try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("DELETE FROM cart WHERE username='"+username+"' and id='"+product+"'");
			System.out.println("Hello");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Cart1.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e)
		{
			System.out.println(e);
			}
%>