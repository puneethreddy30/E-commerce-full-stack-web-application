<%@ include file="site_lheader.jsp" %>
<%@ include file="site_sidebar.jsp" %>
<%@page import="com.mongodb.MongoClient,com.mongodb.MongoException,com.mongodb.WriteConcern,com.mongodb.DB,com.mongodb.DBCollection,com.mongodb.BasicDBObject,com.mongodb.DBObject,com.mongodb.DBCursor,java.io.IOException,java.io.*,java.sql.*,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpSession,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.RequestDispatcher" %>
	

<%
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
	%>
<%@ include file="site_footer.jsp" %>
