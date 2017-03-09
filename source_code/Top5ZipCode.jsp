<%@page import="java.util.Map,java.util.HashMap,java.lang.*,com.helper.*,com.helper.Console,java.io.*,com.mongodb.*,org.xml.sax.InputSource,org.xml.sax.helpers.DefaultHandler,javax.xml.parsers.ParserConfigurationException,javax.xml.parsers.SAXParser,javax.xml.parsers.SAXParserFactory,org.xml.sax.SAXException"%>
<%			
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
			out.print("</table>");%>
