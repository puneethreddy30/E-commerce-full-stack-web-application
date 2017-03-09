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
		String cpassword=request.getParameter("cpass_word");
		if(password.equals(cpassword))
		{
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
				Statement stmt=con.createStatement();
				ResultSet rs=stmt.executeQuery("select username from user where username='"+username+"'");
				if(rs.next())
				{
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Username already exists');");  
					out.println("window.location.href = 'Register';");
					out.println("</script>");
				}
				else
				{
					stmt.executeUpdate("insert into user values('"+username+"','"+password+"')");
					con.close();
					stmt.close();
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Your Account has been created. Click Ok to continue.');");  
					out.println("window.location.href = 'Login.jsp';");
					out.println("</script>");
				}
				
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		else
		{
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Password and confirm password does not match.');");  
			out.println("window.location.href = 'Register.sjp';");
			out.println("</script>");
		}
%>