import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.PrintWriter;
import java.io.*;
public class DealMatches extends HttpServlet
{
	final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	final String DB_URL="jdbc:mysql://localhost/bestdeals";
	//Datase crudentials
	final String USER = "puneethreddy30";
	final String PASS = "puneethreddy";
	HttpSession session; 
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		
	String pipe=null;
	boolean flag11=true;
	ArrayList CategoryItems = new ArrayList();
	MySqlDataStoreUtilities mySqlDataStoreUtilities;
		mySqlDataStoreUtilities = new MySqlDataStoreUtilities();
		List<List> productsdeals = mySqlDataStoreUtilities.getproductbydeals();
		
		for (List order: productsdeals)
  		{
  			if(CategoryItems.size()<2 && !CategoryItems.contains(order.get(1)))
  			{
  				BufferedReader reader=new BufferedReader(new FileReader(new File("C:/apache-tomcat-7.0.34/webapps/csj/DealMatches.txt")));
  				pipe=reader.readLine();
  				System.out.println(pipe);
  				if(pipe==null)
  				{
  					out.println("<h2 align='center'>No offers found</h2>");
  					flag1=false;
  					break;
  				}
  				else
  				{
  					String a=(String) order.get(1);
  					do
  					{
  						
  						if(pipe.contains(a))
  						{
  							
  							out.println("<h3>"+pipe+"</h3>");
  							CategoryItems.add(a);
  							break;
  						}
  						
  					}while((pipe=reader.readLine()) !=null);
  					
  				}
  
		}

  	}
	}

			}
			
	
	
	

