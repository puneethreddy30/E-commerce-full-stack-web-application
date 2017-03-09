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

public class Top5Ratings extends HttpServlet
{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
	try{
			MongoClient mongo = new MongoClient("localhost", 27017);
					
	
		DB db = mongo.getDB("CustomerReviews");
		
		DBCollection myReviews = db.getCollection("myReviews");
		
		
			BasicDBObject gtQuery = new BasicDBObject();
			gtQuery.put("reviewRating",new BasicDBObject("$gt",3));
			DBCursor cursor = myReviews.find(gtQuery).sort(new BasicDBObject("reviewRating",-1)).limit(5);
			out.print("<table>");
			while (cursor.hasNext()) {
		
				BasicDBObject obj = (BasicDBObject) cursor.next();				
				// out.print("<table>");
				out.print("<tr>");
				out.print("<td> Product Name: </td>");
				out.print("<td>" +obj.getString("productName")+ "</td>");
				out.print("</tr>");
			
				out.print("<tr>");
				out.print("<td> Review Rating: </td>");
				out.print("<td>" +obj.getString("reviewRating").toString()+ "</td>");
				out.print("</tr>");
				
				
			}
			out.print("</table>");
		}

		}
				
		out.print("</div></div></div>");
	 catch (MongoException e) {
	e.printStackTrace();
	}
	
		}
	
}