import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.sql.*;
import javax.servlet.http.HttpSession;

public class Top5Products extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{

try
{DBObject groupFields = new BasicDBObject( "_id", "$productName");

			groupFields.put("productName", new BasicDBObject( "$sum", 1));
			DBObject group = new BasicDBObject("$group", groupFields );
			DBObject sortFields = new BasicDBObject("productName", -1);
			DBObject sort = new BasicDBObject("$sort", sortFields );
			AggregationOutput output = myReviews.aggregate(group, sort);
			
			out.print("<table>");
			out.print("<tr><td> Product name: </td><td> Number of pieces sold: </td></tr>");
			int i = 1;
			for (DBObject obj : output.results()) {
				// out.print(obj);
				String id = obj.get("_id").toString();
				String times = obj.get("productName").toString();
				
				out.print("<tr><td>" +obj.get("_id").toString()+ "</td><td>" +obj.get("productName").toString()+ "</td></tr>");						
				i++;
				if(i==6)
					break;							
			}
			out.print("</table>");
}
catch(Exception e)
{
	out.prinnt(e);
}	

	}
}