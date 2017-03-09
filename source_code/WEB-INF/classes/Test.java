import com.mongodb.MongoClient;
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
public class SubmitResults extends HttpServlet
{
public static void main(String s[])
{
	MongoClient mongo = new MongoClient("localhost", 27017);
			System.out.println("Done :1 ");			
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CustomerReviews");
			System.out.println("Done :2 ");
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews = db.getCollection("myReview");
			System.out.print("ConnectException");
			BasicDBObject doc = new BasicDBObject("title", "MongoDB").
				append("productName", "Hello").
				append("productCategory", "lolo").
				append("productPrice", "56").
				append("retailerName", "Apple");
							myReviews.insert(doc);
							BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("productName", "Hello");

			DBCursor cursor = myReviews.find(searchQuery);
			System.out.println("Printing Values");
			System.out.println(cursor);
							while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}
			}
}