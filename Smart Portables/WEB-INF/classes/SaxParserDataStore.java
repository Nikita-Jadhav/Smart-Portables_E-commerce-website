
/*********


http://www.saxproject.org/

SAX is the Simple API for XML, originally a Java-only API. 
SAX was the first widely adopted API for XML in Java, and is a “de facto” standard. 
The current version is SAX 2.0.1, and there are versions for several programming language environments other than Java. 

The following URL from Oracle is the JAVA documentation for the API

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html


*********/
import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;



/**************

SAX parser use callback function  to notify client object of the XML document structure. 
You should extend DefaultHandler and override the method when parsin the XML document

***************/


public class SaxParserDataStore extends DefaultHandler {
    WearableTechnology wearabletechnology;
    Phone phone;
	Phone discountphone;
    Laptop laptop;
    Accessory accessory;
	VoiceAssistant voiceassistant;
	
    static HashMap<String,WearableTechnology> wearabletechnologys;
    static HashMap<String,Phone> phones;
	static HashMap<String,Phone> discountphones;
    static HashMap<String,Laptop> laptops;
	static HashMap<String,VoiceAssistant> voiceassistants;
    static HashMap<String,Accessory> accessories;
    
	String wearabletechnologyXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
	/*
	public static void main(String[] argv) {


	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
	 try {

            Date date = formatter.parse(elementValueRead);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
	}*/
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String wearabletechnologyXmlFileName) {
    this.wearabletechnologyXmlFileName = wearabletechnologyXmlFileName;
    wearabletechnologys = new HashMap<String, WearableTechnology>();
	phones=new  HashMap<String, Phone>();
	discountphones=new HashMap<String,Phone>();
	laptops=new HashMap<String, Laptop>();
	voiceassistants=new HashMap<String, VoiceAssistant>();
	accessories=new HashMap<String, Accessory>();
	accessoryHashMap=new HashMap<String,String>();
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(wearabletechnologyXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

   

////////////////////////////////////////////////////////////

/*************

There are a number of methods to override in SAX handler  when parsing your XML document :

    Group 1. startDocument() and endDocument() :  Methods that are called at the start and end of an XML document. 
    Group 2. startElement() and endElement() :  Methods that are called  at the start and end of a document element.  
    Group 3. characters() : Method that is called with the text content in between the start and end tags of an XML document element.


There are few other methods that you could use for notification for different purposes, check the API at the following URL:

https://docs.oracle.com/javase/7/docs/api/org/xml/sax/helpers/DefaultHandler.html

***************/

////////////////////////////////////////////////////////////
	
	// when xml start element is parsed store the id into respective hashmap for wearabletechnology,phones etc 
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("wearabletechnology")) 
		{
			currentElement="wearabletechnology";
			wearabletechnology = new WearableTechnology();
            wearabletechnology.setId(attributes.getValue("id"));
		}
        if (elementName.equalsIgnoreCase("phone"))
		{
			currentElement="phone";
			phone = new Phone();
            phone.setId(attributes.getValue("id"));

        }
		if (elementName.equalsIgnoreCase("discountphone")) 
		{
			currentElement="discountphone";
			discountphone = new Phone();
            discountphone.setName(attributes.getValue("name"));
		}
        if (elementName.equalsIgnoreCase("laptop"))
		{
			currentElement="laptop";
			laptop= new Laptop();
            laptop.setId(attributes.getValue("id"));
        }
		if (elementName.equalsIgnoreCase("voiceassistant"))
		{
			currentElement="voiceassistant";
			voiceassistant= new VoiceAssistant();
            voiceassistant.setId(attributes.getValue("id"));
        }
        if (elementName.equals("accessory") &&  !currentElement.equals("laptop"))
		{
			currentElement="accessory";
			accessory=new Accessory();
			accessory.setId(attributes.getValue("id"));
	    }


    }
	// when xml end element is parsed store the data into respective hashmap for wearabletechnology,phones etc respectively
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("wearabletechnology")) {
			wearabletechnologys.put(wearabletechnology.getId(),wearabletechnology);
			return;
        }
 
        if (element.equals("laptop")) {	
			laptops.put(laptop.getId(),laptop);
			return;
        }
        if (element.equals("phone")) {	 

			phones.put(phone.getId(),phone);
			return;
        }
		if(element.equals("discountphone")) {
			discountphones.put(discountphone.getName(),discountphone);
			return;
		}	
		if (element.equals("voiceassistant")) {	

			voiceassistants.put(voiceassistant.getId(),voiceassistant);
			return;
        }
        if (element.equals("accessory") && currentElement.equals("accessory")) {
			accessories.put(accessory.getId(),accessory);       
			return; 
        }
		if (element.equals("accessory") && currentElement.equals("laptop")) 
		{
			accessoryHashMap.put(elementValueRead,elementValueRead);
		}
      	if (element.equalsIgnoreCase("accessories") && currentElement.equals("laptop")) {
			laptop.setAccessories(accessoryHashMap);
			accessoryHashMap=new HashMap<String,String>();
			return;
		}

        if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setImage(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setImage(elementValueRead);
			if(currentElement.equals("discountphone"))
				discountphone.setImage(elementValueRead);
            if(currentElement.equals("laptop"))
				laptop.setImage(elementValueRead);
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setImage(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setImage(elementValueRead);          
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setDiscount(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("discountphone"))
				discountphone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setCondition(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setCondition(elementValueRead);
			if(currentElement.equals("discountphone"))
				discountphone.setCondition(elementValueRead);
            if(currentElement.equals("laptop"))
				laptop.setCondition(elementValueRead);
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setCondition(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setCondition(elementValueRead);          
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setRetailer(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setRetailer(elementValueRead);
			if(currentElement.equals("discountphone"))
				discountphone.setRetailer(elementValueRead);
            if(currentElement.equals("laptop"))
				laptop.setRetailer(elementValueRead);
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setRetailer(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setRetailer(elementValueRead);          
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setName(elementValueRead);
        	if(currentElement.equals("phone"))
				phone.setName(elementValueRead);
			if(currentElement.equals("discountphone"))
				discountphone.setName(elementValueRead);
            if(currentElement.equals("laptop"))
				laptop.setName(elementValueRead);
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setName(elementValueRead);
            if(currentElement.equals("accessory"))
				accessory.setName(elementValueRead);          
			return;
	    }
		
		
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setPrice(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("discountphone"))
				discountphone.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
				laptop.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setPrice(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setPrice(Double.parseDouble(elementValueRead));          
			return;
        }
		
		if(element.equalsIgnoreCase("warranty")){
			if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setWarranty(Integer.parseInt(elementValueRead));
			//System.out.println("hhhhhhhhhhhhhhhhh"+Integer.parseInt(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setWarranty(Integer.parseInt(elementValueRead));
			if(currentElement.equals("discountphone"))
				discountphone.setWarranty(Integer.parseInt(elementValueRead));
            if(currentElement.equals("laptop"))
				laptop.setWarranty(Integer.parseInt(elementValueRead));
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setWarranty(Integer.parseInt(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setWarranty(Integer.parseInt(elementValueRead));          
			return;
        }
		
		if(element.equalsIgnoreCase("discount")){
			if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setDiscount(Double.parseDouble(elementValueRead));
			//System.out.println("hhhhhhhhhhhhhhhhh"+Integer.parseInt(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("discountphone"))
				discountphone.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
				laptop.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setDiscount(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setDiscount(Double.parseDouble(elementValueRead));          
			return;
        }
		if(element.equalsIgnoreCase("quantity")){
			if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setQuantity(Integer.parseInt(elementValueRead));
			//System.out.println("hhhhhhhhhhhhhhhhh"+Integer.parseInt(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setQuantity(Integer.parseInt(elementValueRead));
			if(currentElement.equals("discountphone"))
				discountphone.setQuantity(Integer.parseInt(elementValueRead));
            if(currentElement.equals("laptop"))
				laptop.setQuantity(Integer.parseInt(elementValueRead));
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setQuantity(Integer.parseInt(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setQuantity(Integer.parseInt(elementValueRead));          
			return;
        }
		if(element.equalsIgnoreCase("rebate")){
			if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setRebate(Double.parseDouble(elementValueRead));
        	if(currentElement.equals("phone"))
				phone.setRebate(Double.parseDouble(elementValueRead));
			if(currentElement.equals("discountphone"))
				discountphone.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("laptop"))
				laptop.setRebate(Double.parseDouble(elementValueRead));
			if(currentElement.equals("voiceassistant"))
				voiceassistant.setRebate(Double.parseDouble(elementValueRead));
            if(currentElement.equals("accessory"))
				accessory.setRebate(Double.parseDouble(elementValueRead));          
			return;
        }
		/*
		if(element.equalsIgnoreCase("orderdate")){
		
            
               if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setOrderdate(elementValueRead);
               if(currentElement.equals("phone"))
				phone.setOrderdate(elementValueRead);
               if(currentElement.equals("laptop"))
				laptop.setOrderdate(elementValueRead);
               if(currentElement.equals("voiceassistant"))
				voiceassistant.setOrderdate(elementValueRead);
            
               if(currentElement.equals("accessory"))
				accessory.setOrderdate(elementValueRead); 
      
			return;
        }*/
		/*
		if(element.equalsIgnoreCase("deliverydate")){
			
           
               if(currentElement.equals("wearabletechnology"))
				wearabletechnology.setDeliverydate(elementValueRead);
            
               if(currentElement.equals("phone"))
				phone.setDeliverydate(elementValueRead);
               if(currentElement.equals("laptop"))
				laptop.setDeliverydate(elementValueRead);

               if(currentElement.equals("voiceassistant"))
				voiceassistant.setDeliverydate(elementValueRead);
            
               if(currentElement.equals("accessory"))
				accessory.setDeliverydate(elementValueRead); 
            	
			return;
        }*/

	}
	//get each element in xml tag
    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }


    /////////////////////////////////////////
    // 	     Kick-Start SAX in main       //
    ////////////////////////////////////////
	
//call the constructor to parse the xml and get product details
 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"\\webapps\\smt\\ProductCatalog.xml");
    } 
}
