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
public class Top5ZipCode extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
	try
{
	
DBObject groupFields = new BasicDBObject( "_id", "$retailerzip");

			groupFields.put("RetailerZip", new BasicDBObject( "$sum", 1));
			DBObject group = new BasicDBObject("$group", groupFields );
			DBObject sortFields = new BasicDBObject("RetailerZip", -1);
			DBObject sort = new BasicDBObject("$sort", sortFields );
			AggregationOutput output = myReviews.aggregate(group, sort);
			
			out.print("<table>");
			out.print("<tr><td> ZIP Code: </td><td> Number of products sold: </td></tr>");
			int i = 1;
			for (DBObject obj : output.results()) {
				
				String id = obj.get("_id").toString();
				String times = obj.get("RetailerZip").toString();
				
				out.print("<tr><td>" +obj.get("_id").toString()+ "</td><td>" +obj.get("RetailerZip").toString()+ "</td></tr>");						
				i++;
				if(i==6)
					break;
					
			}
}
			catch(Exception e)
			{
				out.print(e);
			}
			out.print("</table>");	
	}
}