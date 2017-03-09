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

public class SubmitReviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
		
		output.println("Started");
		output.println("       ");
		
		try{
			//Get the values from the form
			String productName = request.getParameter("productModelName");
			String productCategory = request.getParameter("productCategory");
			int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			String retailerName = request.getParameter("retailerName");
			String RetailerZip = request.getParameter("RetailerZip");
			String RetailerCity = request.getParameter("RetailerCity");
			String RetailerState = request.getParameter("RetailerState");
			String ProductOnSale = request.getParameter("ProductOnSale");
			String ManufacturerName = request.getParameter("ManufacturerName");
			String ManufacturerRebate = request.getParameter("ManufacturerRebate");
			String userID = request.getParameter("userID");
			int userAge = Integer.parseInt(request.getParameter("userAge"));
			String userGender = request.getParameter("userGender");
			String userOccupation = request.getParameter("userOccupation");
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
			String reviewDate = request.getParameter("reviewDate");
			String reviewText = request.getParameter("reviewText");
			
			// Connect to Mongo DB
			MongoClient mongo = new MongoClient("localhost", 27017);
						
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CustomerReviews");
			
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myReviews = db.getCollection("myReviews");
			System.out.println("Collection myReviews selected successfully");
			
			BasicDBObject doc = new BasicDBObject("title", "MongoDB").
				append("productName", productName).
				append("productCategory", productCategory).
				append("productPrice", productPrice).
				append("retailerName", retailerName).
				append("RetailerZip", RetailerZip).
				append("RetailerCity", RetailerCity).
				append("RetailerState", RetailerState).
				append("ProductOnSale", ProductOnSale).
				append("ManufacturerName", ManufacturerName).
				append("ManufacturerRebate", ManufacturerRebate).
				append("userID", userID).
				append("userAge", userAge).
				append("userGender", userGender).
				append("userOccupation", userOccupation).
				append("reviewRating", reviewRating).
				append("reviewDate", reviewDate).
				append("reviewText", reviewText);
				
			myReviews.insert(doc);
			
			System.out.println("Document inserted successfully");
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("productName", productName);

			DBCursor cursor = myReviews.find(searchQuery);
			output.println("Printing Values");
			output.println(cursor);
			
			while (cursor.hasNext()) {
				output.println(cursor.next());
			}

	    } catch (MongoException e) {
		e.printStackTrace();
	    }
		
		output.println("       ");
		output.println("Done");
	}
	
}

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

public class ViewReviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//Get the values from the form
			String searchField = "productName";
			
			//Get the product selected
			String searchParameter = "";
			if (request.getParameter("Laptops") != null){
			searchParameter = "laptops";
			}else if (request.getParameter("tv") != null){
			searchParameter = "tv";
			}else if (request.getParameter("mobiles") != null){
			searchParameter = "mobiles";
			}else if (request.getParameter("tablet") != null){
			searchParameter = "tablet";
			}
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			DBCollection myReviews = db.getCollection("myReviews");
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(searchField, searchParameter);

			DBCursor cursor = myReviews.find(searchQuery);
			
			PrintWriter out = response.getWriter();
			//out.println(cursor);
						
			out.println("<html>");
			out.println("<head> </head>");
			out.println("<body>");
			out.println("<h1> Reviews For:"+ searchParameter+ "</h1>");
			
			out.println("<table>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='index.html'> Index </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='mobiles.html'> X Box </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>");
			out.println("<a href='tablets.html'> Play Station </a>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("</table>");
			out.println("<br><br><hr>");
			
			if(cursor.count() == 0){
				out.println("There are no reviews for this product.");
			}else{
			
				out.println("<table>");
				
				String productName = "";
				String userName = "";
				String reviewRating = "";
				String reviewDate =  "";
				String reviewText = "";
				
				while (cursor.hasNext()) {
					//out.println(cursor.next());
					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					productName = obj.getString("productName");
					out.println("<td>" +productName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> User Name: </td>");
					userName = obj.getString("userName");
					out.println("<td>" +userName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Rating: </td>");
					reviewRating = obj.getString("reviewRating").toString();
					out.println("<td>" +reviewRating+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Date: </td>");
					reviewDate = obj.getString("reviewDate");
					out.println("<td>" +reviewDate+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Text: </td>");
					reviewText = obj.getString("reviewText");
					out.println("<td>" +reviewText+ "</td>");
					out.println("</tr>");
					
				}
			}	
				out.println("</table>");
				out.println("</body>");
				out.println("</html>");
			
		} catch (MongoException e) {
				e.printStackTrace();
		}
	}

	
	public void destroy(){
      // do nothing.
	}
}import java.io.*;
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