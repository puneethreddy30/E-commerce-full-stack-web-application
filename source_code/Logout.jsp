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
session.invalidate();
response.sendRedirect("index.jsp");
%>
<%@ include file="site_footer.jsp" %>
