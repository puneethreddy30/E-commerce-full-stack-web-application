import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class SaxParser4GameSpeedXMLdataStore extends DefaultHandler {
    Product pr;
    List<Product> prs;
    String xmlFileName;
    String valueRead;
	HttpSession session;

    
    public SaxParser4GameSpeedXMLdataStore(String xmlFileName,HttpSession session) {
        this.xmlFileName = xmlFileName;
        prs = new ArrayList<Product>();
        parseDocument();
    }

    private void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
			e.printStackTrace();
        }
    }

/*private void prettyPrint() {
	
        return prs;
    }*/




    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("product")) {
            pr = new Product();
            pr.setId(attributes.getValue("id"));
            pr.setRetailer(attributes.getValue("retailer"));
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("product")) {
            prs.add(pr);
	    return;
        }
        if (element.equalsIgnoreCase("image")) {
            pr.setImage(valueRead);
	    return;
        }
        if (element.equalsIgnoreCase("type")) {
            pr.setType(valueRead);
	    return;
        }
        if (element.equalsIgnoreCase("name")) {
            pr.setName(valueRead);
	    return;
        }
        if(element.equalsIgnoreCase("accessory")){
           pr.getAccessories().add(valueRead);
	    return;
        }
        if(element.equalsIgnoreCase("price")){
            pr.setPrice(Integer.parseInt(valueRead));
	    return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        valueRead = new String(content, begin, end);
    }


    

}
