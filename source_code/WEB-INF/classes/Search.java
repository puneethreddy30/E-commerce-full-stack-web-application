import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;

public class Search extends HttpServlet
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
		String name=request.getParameter("val");
if(name==null||name.trim().equals("")){
out.print("<p>Please enter product name!</p>");
}else{
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
Statement ps=con.createStatement();
ResultSet rs=ps.executeQuery("select productname,productprice from products where productname like '"+name+"%'");

if(!rs.isBeforeFirst()) {    
 out.println("<p>No Products Found!</p>"); 
}else{
out.print("<table border='1' cellpadding='2' width='100%'>");
out.print("<tr><th>Product Name</th><th>Product Price</th></tr>");
while(rs.next()){
String pname=rs.getString("productname");
String pprice=rs.getString("productprice");
out.print("<tr><td><b><a href='AjaxView?maker="+pname+"'>"+pname+"</a></b></td><td><b>"+pprice+"</b></td></tr>");
}
out.print("</table>");
}//end of else for rs.isBeforeFirst
con.close();
}catch(Exception e){out.print(e);}
}//end of else
		}
	
}