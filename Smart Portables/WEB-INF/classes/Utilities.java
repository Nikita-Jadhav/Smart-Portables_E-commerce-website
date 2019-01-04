import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@WebServlet("/Utilities")

/* 
	Utilities class contains class variables of type HttpServletRequest, PrintWriter,String and HttpSession.

	Utilities class has a constructor with  HttpServletRequest, PrintWriter variables.
	  
*/

public class Utilities extends HttpServlet{
	HttpServletRequest req;
	PrintWriter pw;
	String url;
	HttpSession session; 
	public Utilities(HttpServletRequest req, PrintWriter pw) {
		this.req = req;
		this.pw = pw;
		this.url = this.getFullURL();
		this.session = req.getSession(true);
	}



	/*  Printhtml Function gets the html file name as function Argument, 
		If the html file name is Header.html then It gets Username from session variables.
		Account ,Cart Information ang Logout Options are Displayed*/

	public void printHtml(String file) {
		String result = HtmlToString(file);
		//to print the right navigation in header of username cart and logout etc
		if (file == "Header.html") {
				result=result+"<div id='menu' style='float: right;'><ul>";
			if (session.getAttribute("username")!=null){
				String username = session.getAttribute("username").toString();
				username = Character.toUpperCase(username.charAt(0)) + username.substring(1);
				if(session.getAttribute("usertype").equals("manager"))
				{
					result = result + "<li><a href='ProductModify?button=Addproduct'><span class='glyphicon'>AddProduct</span></a></li>"
						+ "<li><a href='ProductModify?button=Updateproduct'><span class='glyphicon'>UpdateProduct</span></a></li>"
						+ "<li><a href='ProductModify?button=Deleteproduct'><span class='glyphicon'>DeleteProduct</span></a></li>"
						/*+ "<li class='dropdown main-menu-bar'><a href='javascript:void(0)' class='dropdown-toggle'><span class='glyphicon'>InventoryReport</span></a><ul class=dropdown-menu dropdown-second-menu><li><a href='#'>Products Availability</a></li><li><a href='#'>Graphical Representation(Availability)</a></li><li><a href='#'>Products On Sale</a></li><li><a href='#'>Products with Manufacturer Rebates</a></li></ul></li>"
						*/
						/*+ "<div class='dropdown'><a class='btn btn-secondary dropdown-toggle' href='#' role='button' id='dropdownMenuLink' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>InventoryReport</a><div class='dropdown-menu' aria-labelledby='dropdownMenuLink'><a class='dropdown-item' href='#'>Action</a><a class='dropdown-item' href='#'>Another action</a><a class='dropdown-item' href='#'>Something else here</a></div></div>"
						*/
						+ "<li><a href='ProductModify?button=Dataexplorationutility'><span class='glyphicon'>DataExploration</span></a></li>"
						+ "<li><a href='ProductModify?button=Inventoryreport'><span class='glyphicon'>InventoryReport</span></a></li>"
						+ "<li><a href='ProductModify?button=Salesreport'><span class='glyphicon'>SalesReport</span></a></li>"
						+ "<li><a href='DataVisualization'><span class='glyphicon'>Trending</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				/*<li class="dropdown main-menu-bar" role="presentation">
                            <a class="dropdown-toggle" id="idHome" href='#' role="button" aria-haspopup="true" aria-expanded="false">SPECIALTIES 
<span class="caret"></span></a>
                             <ul class=dropdown-menu dropdown-second-menu><li><a href='/specialties'>Specialties</a></li><li><a href='/specialties/speciality-clinic'>Speciality Clinic</a></li><li><a href='/specialties/health-check-up'>Health Check up</a></li><li><a href='/specialitywise-opd'>OPD Scheduler</a></li><li><a href='/doctorwise-opd'>Our Specialists</a></li></ul>
                        </li>*/

				else if(session.getAttribute("usertype").equals("retailer")){
					result = result + "<li><a href='Signup'><span class='glyphicon'>Create Customer</span></a></li>"
						+ "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
				}
				else
				{
				result = result + "<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"
						+ "<li><a><span class='glyphicon'>Hello,"+username+"</span></a></li>"
						+ "<li><a href='Account'><span class='glyphicon'>Account</span></a></li>"
						+ "<li><a href='Logout'><span class='glyphicon'>Logout</span></a></li>";
			    }
			}
			else
				result = result +"<li><a href='ViewOrder'><span class='glyphicon'>ViewOrder</span></a></li>"+ "<li><a href='Login'><span class='glyphicon'>Login</span></a></li>";
				result = result +"<li><a href='ViewCart'><span class='glyphicon glyphicon-shopping-cart'>ViewCart("+CartCount()+")</span></a></li>"
						+"</ul></div></div></nav></div><div id='page'>"
						+"<img src='img12.jpg' alt='Placeholder image' width='2000' height='150'/><div class='clear'></div></div>";
						pw.print(result);
		} else
				pw.print(result);
	}
	
	

	/*  getFullURL Function - Reconstructs the URL user request  */

	public String getFullURL() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		StringBuffer url = new StringBuffer();
		url.append(scheme).append("://").append(serverName);

		if ((serverPort != 80) && (serverPort != 443)) {
			url.append(":").append(serverPort);
		}
		url.append(contextPath);
		url.append("/");
		return url.toString();
	}

	/*  HtmlToString - Gets the Html file and Converts into String and returns the String.*/
	public String HtmlToString(String file) {
		String result = null;
		try {
			String webPage = url + file;
			URL url = new URL(webPage);
			URLConnection urlConnection = url.openConnection();
			InputStream is = urlConnection.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);

			int numCharsRead;
			char[] charArray = new char[1024];
			StringBuffer sb = new StringBuffer();
			while ((numCharsRead = isr.read(charArray)) > 0) {
				sb.append(charArray, 0, numCharsRead);
			}
			result = sb.toString();
		} 
		catch (Exception e) {
		}
		return result;
	} 

	/*  logout Function removes the username , usertype attributes from the session variable*/

	public void logout(){
		session.removeAttribute("username");
		session.removeAttribute("usertype");
	}
	
	/*  logout Function checks whether the user is loggedIn or Not*/

	public boolean isLoggedin(){
		if (session.getAttribute("username")==null)
			return false;
		return true;
	}

	/*  username Function returns the username from the session variable.*/
	
	public String username(){
		if (session.getAttribute("username")!=null)
			return session.getAttribute("username").toString();
		return null;
	}
	
	/*  usertype Function returns the usertype from the session variable.*/
	public String usertype(){
		if (session.getAttribute("usertype")!=null)
			return session.getAttribute("usertype").toString();
		return null;
	}
	
	/*  getUser Function checks the user is a customer or retailer or manager and returns the user class variable.*/
	public User getUser(){
		String usertype = usertype();
		HashMap<String, User> hm=new HashMap<String, User>();
		try
		{		
			hm=MySqlDataStoreUtilities.selectUser();
		}
		catch(Exception e)
		{
		}	
		User user = hm.get(username());
		return user;
	}
	
	/*  getCustomerOrders Function gets  the Orders for the user*/
	public ArrayList<OrderItem> getCustomerOrders(){
		ArrayList<OrderItem> order = new ArrayList<OrderItem>(); 
		if(OrdersHashMap.orders.containsKey(username()))
			order= OrdersHashMap.orders.get(username());
		return order;
	}

	/*  getOrdersPaymentSize Function gets  the size of OrderPayment */
	public int getOrderPaymentSize(){
		
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		int size=0;
		try
		{
			orderPayments =MySqlDataStoreUtilities.selectOrder();
				
		}
		catch(Exception e)
		{
			
		}
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet()){
				size=entry.getKey();
		}
			
		return size;		
	}

	/*  CartCount Function gets  the size of User Orders*/
	public int CartCount(){
		if(isLoggedin())
		return getCustomerOrders().size();
		return 0;
	}
	
	/* StoreProduct Function stores the Purchased product in Orders HashMap according to the User Names.*/

	public void storeProduct(String name,String type,String creator, String acc){
		if(!OrdersHashMap.orders.containsKey(username())){	
			ArrayList<OrderItem> arr = new ArrayList<OrderItem>();
			OrdersHashMap.orders.put(username(), arr);
		}
		ArrayList<OrderItem> orderItems = OrdersHashMap.orders.get(username());
		HashMap<String,WearableTechnology> allwearabletechnologys = new HashMap<String,WearableTechnology> ();
			HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();
			HashMap<String,Phone> allphones = new HashMap<String,Phone> ();
			HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
			HashMap<String,VoiceAssistant> allvoiceassistants=new HashMap<String,VoiceAssistant>();
			HashMap<String,Phone> alldiscountphones = new HashMap<String,Phone> ();
			
			
		if(type.equals("wearabletechnologys")){
			WearableTechnology wearabletechnology;
			try{
			allwearabletechnologys=MySqlDataStoreUtilities.getWearableTechnologys();
			System.out.println("WTT="+allwearabletechnologys.size());
			}
			catch(Exception e){
			}
		    wearabletechnology = allwearabletechnologys.get(name);
			  /*System.out.println("WTT="+allwearabletechnologys.get(name));
			  System.out.println("WTT="+allwearabletechnologys.getId());
			  System.out.println("WTT="+allwearabletechnologys.getName());
			  System.out.println("WTT="+allwearabletechnologys.getPrice());*/
			OrderItem orderitem = new OrderItem(wearabletechnology.getId(),wearabletechnology.getName(), wearabletechnology.getPrice(), wearabletechnology.getImage(), wearabletechnology.getRetailer(), wearabletechnology.getWarranty(),wearabletechnology.getDiscount());
			orderItems.add(orderitem);

		}
		if(type.equals("phones")){
			Phone phone = null;
			try{
			allphones = MySqlDataStoreUtilities.getPhones();
			System.out.println("WTT="+allphones.size());
			}
			catch(Exception e){
				
			}
			phone = allphones.get(name);
			OrderItem orderitem = new OrderItem(phone.getId(),phone.getName(), phone.getPrice(), phone.getImage(), phone.getRetailer(), phone.getWarranty(),phone.getDiscount());
			orderItems.add(orderitem);
		}
		/*if(type.equals("discountphones")){
			Phone discountphone = null;
			try{
			alldiscountphones = MySqlDataStoreUtilities.getDiscountedproductnames();
			System.out.println("WTT="+alldiscountphones.size());
			}
			catch(Exception e){
				
			}
			discountphone = alldiscountphones.get(name);
			OrderItem orderitem = new OrderItem(discountphone.getId(),discountphone.getName(), discountphone.getPrice(), discountphone.getImage(), discountphone.getRetailer(), discountphone.getWarranty(),discountphone.getDiscount());
			orderItems.add(orderitem);
		}*/
		if(type.equals("laptops")){
			Laptop laptop = null;
			try{
			alllaptops = MySqlDataStoreUtilities.getLaptops();
			}
			catch(Exception e){
				
			}
			laptop = alllaptops.get(name);
			OrderItem orderitem = new OrderItem(laptop.getId(),laptop.getName(), laptop.getPrice(), laptop.getImage(), laptop.getRetailer(), laptop.getWarranty(),laptop.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("voiceassistants")){
			VoiceAssistant voiceassistant = null;
			try{
			allvoiceassistants = MySqlDataStoreUtilities.getVoiceAssistants();
			}
			catch(Exception e){
				
			}
			voiceassistant = allvoiceassistants.get(name);
			OrderItem orderitem = new OrderItem(voiceassistant.getId(),voiceassistant.getName(), voiceassistant.getPrice(), voiceassistant.getImage(), voiceassistant.getRetailer(),voiceassistant.getWarranty(),voiceassistant.getDiscount());
			orderItems.add(orderitem);
		}
		if(type.equals("accessories")){	
		try{
			allaccessories = MySqlDataStoreUtilities.getAccessories();
			}
			catch(Exception e){
				
			}
			Accessory accessory = allaccessories.get(name); 
			OrderItem orderitem = new OrderItem(accessory.getId(),accessory.getName(), accessory.getPrice(), accessory.getImage(), accessory.getRetailer(), accessory.getWarranty(),accessory.getDiscount());
			orderItems.add(orderitem);
		}
		
	}
	// store the payment details for orders
	public void storePayment(int orderId,
		String orderName,double orderPrice,String userAddress,String creditCardNo,String customer, int warrantyamt, double discount,String orderdate,String deliverydate,String state){
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments= new HashMap<Integer, ArrayList<OrderPayment>>();
		try
		{
			orderPayments=MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{
			
		}
		if(orderPayments==null)
		{
			orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		}
			// if there exist order id already add it into same list for order id or create a new record with order id
			
		if(!orderPayments.containsKey(orderId)){	
			ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
			orderPayments.put(orderId, arr);
		}
		ArrayList<OrderPayment> listOrderPayment = orderPayments.get(orderId);		
		
		OrderPayment orderpayment = new OrderPayment(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,discount,warrantyamt,orderdate,deliverydate,state);
		listOrderPayment.add(orderpayment);	
			
			// add order details into database
		try
		{	if(session.getAttribute("usertype").equals("retailer"))
			{
				MySqlDataStoreUtilities.insertOrder(orderId,customer,orderName,orderPrice,userAddress,creditCardNo,discount,warrantyamt,orderdate,deliverydate,state);
			}
			else
				{MySqlDataStoreUtilities.insertOrder(orderId,username(),orderName,orderPrice,userAddress,creditCardNo,discount,warrantyamt,orderdate,deliverydate,state);}
		}
		catch(Exception e)
		{
			System.out.println("inside exception file not written properly");
		}	
	}
	//productname,producttype,productmaker,reviewrating,reviewdate,reviewtext,retailername,retailerpin,retailercity,retailerstate,productprice,productonsale,age,userid,gender,occupation,discount
     public String storeReview(String productname,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String retailername,String retailerpin,String retailercity,String retailerstate,String productprice,String productonsale,String age,String userid,String gender,String occupation,String rebate,String state){
	String message=MongoDBDataStoreUtilities.insertReview(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,retailername,retailerpin,retailercity,retailerstate,productprice,productonsale,age,userid,gender,occupation,rebate,state);
		if(!message.equals("Successfull"))
		{ return "UnSuccessfull";
		}
		else
		{
		HashMap<String, ArrayList<Review>> reviews= new HashMap<String, ArrayList<Review>>();
		try
		{
			reviews=MongoDBDataStoreUtilities.selectReview();
		}
		catch(Exception e)
		{
			
		}
		if(reviews==null)
		{
			reviews = new HashMap<String, ArrayList<Review>>();
		}
			// if there exist product review already add it into same list for productname or create a new record with product name
			
		if(!reviews.containsKey(productname)){	
			ArrayList<Review> arr = new ArrayList<Review>();
			reviews.put(productname, arr);
		}
		ArrayList<Review> listReview = reviews.get(productname);		
		Review review = new Review(productname,username(),producttype,productmaker,reviewrating,reviewdate,reviewtext,retailername,retailerpin,retailercity,retailerstate,productprice,productonsale,age,userid,gender,occupation,rebate,state);
		listReview.add(review);	
			
			// add Reviews into database
		
		return "Successfull";	
		}
	}
	
	
	/* getWearabletechnologys Functions returns the Hashmap with all wearabletechnologys in the store.*/

	public HashMap<String, WearableTechnology> getWearableTechnologys(){
			HashMap<String, WearableTechnology> hm = new HashMap<String, WearableTechnology>();
			hm.putAll(SaxParserDataStore.wearabletechnologys);
			return hm;
	}
	
	/* getPhones Functions returns the  Hashmap with all Phones in the store.*/

	public HashMap<String, Phone> getPhones(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.phones);
			return hm;
	}
	/* getPhones Functions returns the  Hashmap with all discounted Phones in the store.*/
	public HashMap<String, Phone> getDiscountedproductnames(){
			HashMap<String, Phone> hm = new HashMap<String, Phone>();
			hm.putAll(SaxParserDataStore.discountphones);
			return hm;
			
	}
	/* getLaptops Functions returns the Hashmap with all Laptop in the store.*/

	public HashMap<String, Laptop> getLaptops(){
			HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
			hm.putAll(SaxParserDataStore.laptops);
			return hm;
	}
	
	/* getVoiceassistants Functions returns the Hashmap with all Voiceassistant in the store.*/

	public HashMap<String, VoiceAssistant> getVoiceAssistants(){
			HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();
			hm.putAll(SaxParserDataStore.voiceassistants);
			return hm;
	}
	
	
	
	/* getProducts Functions returns the Arraylist of wearabletechnologys in the store.*/

	public ArrayList<String> getProducts(){
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Laptop> entry : getLaptops().entrySet()){			
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of phones in the store.*/

	public ArrayList<String> getProductsPhone(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, Phone> entry : getPhones().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Laptops in the store.*/

	public ArrayList<String> getProductsWearableTechnologys(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, WearableTechnology> entry : getWearableTechnologys().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
	/* getProducts Functions returns the Arraylist of Voiceassistants in the store.*/

	public ArrayList<String> getProductsVoiceassistants(){		
		ArrayList<String> ar = new ArrayList<String>();
		for(Map.Entry<String, VoiceAssistant> entry : getVoiceAssistants().entrySet()){
			ar.add(entry.getValue().getName());
		}
		return ar;
	}
	
}
