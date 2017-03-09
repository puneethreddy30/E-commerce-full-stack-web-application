<%@ include file="sales_header.jsp" %>
<%@page import="java.io.IOException,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
	
<%!
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
		
		
%>
<%
out.print("<section id=\"content\"><!--<b>Product Added !</b>--><h2>Add Product Here</h2><form action=\"InsertProduct.jsp\"><table><tr><td>Name of product</td><td><input name=\"name\" type=\"text\"></td></tr>");
		out.print("<tr><td>Price of product</td><td><input name=\"price\" type=\"text\"></td></tr>");
		out.print("<tr><td>Type of Product</td><td><input name=\"ptype\" type=\"text\"></td></tr>");
		out.print("<tr><td>Image Link</td><td><input name=\"link\" type=\"text\"></td></tr><tr><td></td><td><input type=\"submit\"></td></tr></form></table></form>");
%>
<%@ include file="site_footer.jsp" %>