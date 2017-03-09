<%@ include file="sales_header.jsp" %>
<%@page import="java.io.IOException,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
	
<%!
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
		
		
%>
<%
try
		{
			String price=request.getParameter("name");
			String pname=request.getParameter("price");
			String ptype=request.getParameter("ptype");
			String plink=request.getParameter("link");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			int i=stmt.executeUpdate("insert into products values('"+price+"','"+pname+"','"+ptype+"','"+plink+"')");
			if(i>0)
			{
				out.println("<script type=\"text/javascript\">");  
			out.println("alert('Product Added');");  
			out.println("window.location.href = 'StoreManager.jsp';");
			out.println("</script>");
				
			
			}
		}
catch(Exception e)
{
	out.print(e);
}
%>
<%@ include file="site_footer.jsp" %>