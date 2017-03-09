<%@page import="java.io.IOException,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
<%!
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";
		final String DB_URL="jdbc:mysql://localhost/bestdeals";
		final String USER = "puneethreddy30";
		final String PASS = "puneethreddy";
%>
<%
		String username=request.getParameter("user_name");
		String password=request.getParameter("pass_word");
		if(username.equals("salesadmin")&&password.equals("salesadmin"))
			response.sendRedirect("SalesManager.jsp");
		else if(username.equals("storeadmin")&&password.equals("storeadmin"))
			response.sendRedirect("StoreManager.jsp");	
		
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select username,password from user where username='"+username+"' and password='"+password+"'");
				if(rs.next())
				{
				String db_user=rs.getString("username");	
				String db_pass=rs.getString("password");
				session = request.getSession();
				session.setAttribute("username",username);
				RequestDispatcher dispatcher = request.getRequestDispatcher("Home.jsp");
				dispatcher.forward(request, response);
				
				}
				else
				{
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('The details you have entered is incorrect. Please try again.');");  
				out.println("window.location.href = 'Login';");
				out.println("</script>");
				}
				
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}

%>