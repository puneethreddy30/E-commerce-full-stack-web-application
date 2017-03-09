import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;

public class AjaxView extends HttpServlet
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/bestdeals";
	//Datase crudentials
	final String USER = "puneethreddy30";
	final String PASS = "puneethreddy";
	HttpSession session; 
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		
		response.setContentType("text/html");
		//HttpSession session=session.getSession(true);
		
		PrintWriter out = response.getWriter();
		String name=request.getParameter("maker");
		All All = new All(request,out);
		All.includeHtml("site_header.html");
	
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
Statement ps=con.createStatement();
ResultSet rs=ps.executeQuery("select productname,productprice from products where productname='"+name+"'");

out.print("<section id=\"content\"><table border='1' cellpadding='2' width='100%'>");
out.print("<tr><th>Product Name</th><th>Product Price</th><th>Action</th></tr>");
while(rs.next()){
	
String pname=rs.getString("productname");
String pprice=rs.getString("productprice");
out.print("<tr><td>"+pname+"</td><td>"+pprice+"</td><td><form action=\"cart1\"><input type=\"hidden\" name=\"name\" value="+pname+"><input type=\"hidden\" name=\"price\" value="+pprice+"><input type=\"submit\	" value=\"add to cart\"></td></tr>");
}
out.print("</table></section>");
All.includeHtml("site_sidebar.html");
		All.includeHtml("site_footer.html");
}
catch(Exception e){out.print(e);}
}	
}		
	

<section id="content">
<h4>Search Products here</h4>
<form name="vinform" action="Search">
<input type="text" name="name" onkeyup="searchInfo()">
</form>