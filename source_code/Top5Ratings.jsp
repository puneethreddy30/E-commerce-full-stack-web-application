<%@page import="java.util.Map,java.util.HashMap,java.lang.*,com.helper.*,com.helper.Console,java.io.*,com.mongodb.*,org.xml.sax.InputSource,org.xml.sax.helpers.DefaultHandler,javax.xml.parsers.ParserConfigurationException,javax.xml.parsers.SAXParser,javax.xml.parsers.SAXParserFactory,org.xml.sax.SAXException"%>
<%	
				
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
	
	

%>