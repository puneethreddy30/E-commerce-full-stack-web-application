<%@ include file="site_lheader.jsp" %>
<%
try {

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			DefaultHandler handler = new DefaultHandler()
			{
				HashMap<String,Console> apple = null;
				HashMap<String,Console> samsung = null;
				
				Console con = null;
				HashMap<String,AdditionalGears> accessories = null;
				AdditionalGears acc = null;
				
				String Maker =  "" ; 
				String apple_String = "apple";
				String samsung_String = "samsung";
				String lg_String = "Lg";
				
				boolean bSmartPhones = false;
				
				boolean bapple = false;
				boolean bsamsung = false;
				
				
				boolean bName = false;
				boolean bPrice = false;
				boolean bImage = false;
				//IDs
				String ProductID = "";
				String AdditionalGearsID = "";
				
				
				public void startElement(String uri, String localName, String qName, Attributes attributes)	throws SAXException 
					{
						
					if(bForMakerTag)
					{
						Maker = qName.toString().toLowerCase();
						bForMakerTag = false;
					}	
					
					if(qName.equalsIgnoreCase("Mobiles"))
					{
						bSmartPhones = true;
						bForMakerTag = true;
					} 
					
					else if(qName.equalsIgnoreCase(Maker))
					
					
					{
						
						switch(Maker){
							case "apple":
								bapple = true;
								break;
							
							case "samsung":
								bsamsung = true;
								break;
								
						}
						
						if(apple == null)
							apple = new HashMap<String,Console>();
						ProductID = attributes.getValue("id");
						con = new Console();
					}

					else if ((!ProductID.trim().isEmpty()) && (qName.equalsIgnoreCase("name"))) 
					{
						bName = true;
					} 
					
					else if ((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase("price")) 
					{
						bPrice = true;
					} 
					
					else if ((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase("image"))
					{
						bImage = true;
					}

							switch(Maker)
							
							{
								case "apple":
									AdditionalGearsID = apple_String;
									break;
									
								case "samsung":
									AdditionalGearsID = samsung_String;
									break;
									
								case "lg":
									AdditionalGearsID = lg_String;
									break;
							}
								
							AdditionalGearsID += attributes.getValue("id");
							acc = new AdditionalGears();
							bAdditionalGears = true;
							
					}

				}

				
				public void endElement(String uri, String localName, String qName) 
						throws SAXException {
					
					if((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase("AdditionalGears"))
					
					{
						accessories.put(AdditionalGearsID,acc);	
						AdditionalGearsID = "";
						bAdditionalGears = false;
					}
					
					if ((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase("Accessories"))

					{
						bAccessories = false;
						con.setAccessories(accessories);
						
						switch(Maker){
							case "apple":
								apple.put(apple_String + ProductID,con);
								break;
							case "samsung":
								apple.put(samsung_String + ProductID,con);
								break;
						}
						     	
					}
					
					if((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase(apple_String))
					
					{
						bapple = false;
						ProductID = "";
						bForMakerTag = true;
					} 
					
					else if((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase(samsung_String))
					
					{
						bsamsung = false;
						ProductID = "";
						bForMakerTag = true;
					} 
					
					
					else if((!ProductID.trim().isEmpty()) && qName.equalsIgnoreCase(lg_String))
					
					{
						bLg = false;
						ProductID = "";
						bForMakerTag = true;
					}
			
					if(qName.equalsIgnoreCase("Mobiles"))
					
					{
						
						outHMap = apple;
						bSmartPhones = false;
						bForMakerTag = false;
					}   
				}

				
				public void characters(char ch[], int start, int length) throws SAXException 
				
				{

					if (bName) 
					
					{
						con.setName(new String(ch, start, length));
						bName = false;
					} 
					else if (bPrice)

					{
						String x = new String(ch, start, length);
						if(x.trim().isEmpty() || x.length() == 0)
							con.setPrice(0);
						else
							con.setPrice(Integer.parseInt(new String(ch, start, length)));
						bPrice = false;
					} 
					
					else if (bImage)

					{
						con.setImage(new String(ch, start, length));
						bImage = false;
					} 
													
								
						}
						
					}
				}

		 };

		   saxParser.parse("c:\\Mobiles.xml",handler);
		
     }
	 catch (Exception e)
	 {
       e.printStackTrace();
     }
%>
<%@ include file="site_sidebar.jsp" %>
<%@ include file="site_ptb.jsp" %>
<%@ include file="site_footer.jsp" %>