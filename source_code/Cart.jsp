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
String name=request.getParameter("name");
		String price=request.getParameter("price");
		int i=0;
		//ResultSet rs=stmt.executeQuery("select username from user where username='"+session+"'");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			stmt.executeUpdate("insert into cart values('"+username+"','"+name+"','"+price+"','"+i+"')");
			ResultSet rs=stmt.executeQuery("select * from cart where username='"+username+"'");//stmt.executeQue
			out.print("<div id=\"content\"><table border=\"1px\"><th>Name</th><th>Price</th><th>Action</th>");
			while(rs.next())
			{
				String pname=rs.getString("productname");
				int pprice=rs.getInt("productprice");
				int id=rs.getInt("id");
				out.print("<tr><td>");
				out.println(pname);
				out.print("</td><td>");
				out.println("$"+pprice);
				out.print("</td><td>");
				out.println("<form action=\"DeleteProduct.jsp\"><input type=\"hidden\" value="+id+" name=\"dname\"><input type=\"submit\" value=\"Delete\"></form>");
				out.print("</td></tr>");
			}
			out.print("</tr></table><a href='ProceedCheckout.jsp'>Proceed to Checkout</a></div>");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
%>
<%@ include file="site_footer.jsp" %>