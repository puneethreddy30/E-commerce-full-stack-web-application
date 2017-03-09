<%@page import="java.util.Map,java.util.HashMap,java.lang.*,com.helper.*,com.helper.Console,java.io.*,com.mongodb.*,org.xml.sax.InputSource,org.xml.sax.helpers.DefaultHandler,javax.xml.parsers.ParserConfigurationException,javax.xml.parsers.SAXParser,javax.xml.parsers.SAXParserFactory,org.xml.sax.SAXException"%>
<%
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
%>