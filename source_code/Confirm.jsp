<%@page import="java.io.IOException,java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
	
<%!
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
		int i=0;
			
		
		
%>
<%
session = request.getSession(true);
String username = session.getAttribute("username").toString();
DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	   //get current date time with Date()
		Date date = new Date();
		String odate=(dateFormat.format(date)).toString();
	

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection(DB_URL,USER,PASS);
			Statement stmt=conn.createStatement();
			String address=request.getParameter("address");
			String credit=request.getParameter("creditcard");
			String total=request.getParameter("total");
			stmt.executeUpdate("DELETE FROM cart WHERE username='"+username+"'");
			int j=stmt.executeUpdate("insert into orders values('"+i+"','"+username+"','"+total+"','"+date+"','"+address+"','"+credit+"')");
			ResultSet rs=stmt.executeQuery("select id from orders where username='"+username+"' and date='"+date+"'");
			if(rs.next())
			{
			int id=rs.getInt("id");
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Order has been place. You can track your order using your order id. Your order id is : "+id+"');");  
			out.println("window.location.href = 'Home.jsp';");
			out.println("</script>");
			}
			else
			out.print("Sorry you cannot place the order at this time. Sorry for inconvinience");
		}
		catch(Exception e)
		{
			System.out.println(e);
			}
%>