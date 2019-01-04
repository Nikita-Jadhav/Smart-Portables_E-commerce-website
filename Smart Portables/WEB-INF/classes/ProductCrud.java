import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.sql.*;
import java.util.ArrayList;
import com.google.gson.Gson;
import org.json.JSONObject;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
@WebServlet("/ProductCrud")

public class ProductCrud extends HttpServlet {
	ResultSet rs = null;
	//ResultSet rs1=null;
	private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		private static final String FILE_HEADER = "TotSales,state";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html");
			PrintWriter pw = response.getWriter();
			Utilities utility = new Utilities(request, pw);
			String action = request.getParameter("button");
			System.out.print(action);
			String msg = "good";
			String producttype= "",productId="",orderName="",productName="",productImage="",productManufacturer="",productCondition="",prod = "",productOrderdate="",productDeliverydate="";
			double productPrice=0.0,productDiscount = 0.0,productRebate=0.0,orderPrice=0.0,totsales=0.0;
			int productWarranty=0,productCount=0,productQuantity=0,count1=0;
			HashMap<String,WearableTechnology> allwearabletechnologys = new HashMap<String,WearableTechnology> ();
			HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();
			HashMap<String,Phone> allphones = new HashMap<String,Phone> ();
			//HashMap<String,Phone> alldiscountphones = new HashMap<String,Phone> ();
			
			
			
			HashMap<String,Accessory> allaccessories=new HashMap<String,Accessory>();
			System.out.println("1");
			if (action.equals("add") || action.equals("update"))
			{	
				 System.out.println("2");
				 producttype = request.getParameter("producttype");
				 productId   = request.getParameter("productId");
				 productName = request.getParameter("productName"); 
				 productPrice = Double.parseDouble(request.getParameter("productPrice"));
				 productImage = request.getParameter("productImage");
				 productManufacturer = request.getParameter("productManufacturer");
				 productCondition = request.getParameter("productCondition");
				 productDiscount = Double.parseDouble(request.getParameter("productDiscount"));
				 //productWarranty = request.getParameter("productWarranty");
				 productWarranty = Integer.parseInt(request.getParameter("productWarranty"));
				 //productCount=Integer.parseInt(request.getParameter("productCount"));
				 productQuantity=Integer.parseInt(request.getParameter("productQuantity"));
				 productRebate=Double.parseDouble(request.getParameter("productRebate"));
				 /*productOrderdate = request.getParameter("productOrderdate");
				 productOrderdate = request.getParameter("productDeliverydate");*/
				 /*orderName = request.getParameter("orderName");
				 orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
				 count1 = Integer.parseInt(request.getParameter("count1"));
				 totsales = Double.parseDouble(request.getParameter("totsales"));*/
			}
			else
			{
				System.out.println("3");
				productId   = request.getParameter("productId");
				System.out.println("PID"+productId);
				//orderName = request.getParameter("orderName");
				/*productPrice = Double.parseDouble(request.getParameter("productPrice"));
				orderPrice = Double.parseDouble(request.getParameter("orderPrice"));
				count1 = Integer.parseInt(request.getParameter("count1"));
				totsales = Double.parseDouble(request.getParameter("totsales"));*/
				
			}	
			utility.printHtml("Header.html");
			utility.printHtml("Left_Nav.html");

			if(action.equals("add"))
			{
			  			if(producttype.equals("wearabletechnologys")){
						  allwearabletechnologys = MySqlDataStoreUtilities.getWearableTechnologys();
						  if(allwearabletechnologys.containsKey(productId)){
							  msg = "Product already available";
							  System.out.print(productId);
						  }
					  
						}
						else if(producttype.equals("laptops"))
						{
							  alllaptops = MySqlDataStoreUtilities.getLaptops();
							  if(alllaptops.containsKey(productId)){
								  msg = "Product already available";
							  }
						  }else if (producttype.equals("phones"))
						  {
							  allphones = MySqlDataStoreUtilities.getPhones();
							  if(allphones.containsKey(productId)){
								  msg = "Product already available";
							  }
						  }else if (producttype.equals("accessories"))
						  {  
								if(!request.getParameter("product").isEmpty())
									{
										prod = request.getParameter("product");
										alllaptops = MySqlDataStoreUtilities.getLaptops();
										if(alllaptops.containsKey(prod))
										{
											allaccessories = MySqlDataStoreUtilities.getAccessories();
											if(allaccessories.containsKey(productId)){
												msg = "Product already available";
											}
										}else{
											msg = "The product related to accessories is not available";
										}
									
									
									}else{
										msg = "Please add the prodcut name";
									}
							  
						  }	
						  if (msg.equals("good"))
						  {  
							  try
							  {
								  msg = MySqlDataStoreUtilities.addproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productWarranty,productQuantity,productRebate,prod);
							  }
							  catch(Exception e)
							  { 
								msg = "Product cannot be inserted";
							  }
							  msg = "Product has been successfully added";
						  }	
pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div></div>");						  
			}
			else if(action.equals("update"))
			{
				
						  if(producttype.equals("wearabletechnologys")){
							  allwearabletechnologys = MySqlDataStoreUtilities.getWearableTechnologys();
							  if(!allwearabletechnologys.containsKey(productId)){
								  msg = "Product not available";
							  }
								  
						  }else if(producttype.equals("laptops"))
						  {
							  alllaptops = MySqlDataStoreUtilities.getLaptops();
							  if(!alllaptops.containsKey(productId)){
								  msg = "Product not available";
							  }
						  }else if (producttype.equals("phones"))
						  {
							  allphones = MySqlDataStoreUtilities.getPhones();
							  if(!allphones.containsKey(productId)){
								  msg = "Product not available";
							  }
						  }else if (producttype.equals("accessories"))
						  {
							  allaccessories = MySqlDataStoreUtilities.getAccessories();
							  if(!allaccessories.containsKey(productId)){
								  msg = "Product not available";
							}
						  }	
						  if (msg.equals("good"))
						  {		
							
							  try
							  {
								msg = MySqlDataStoreUtilities.updateproducts(producttype,productId,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productWarranty,productQuantity,productRebate);
							  }
							  catch(Exception e)
							  { 
								msg = "Product cannot be updated";
							  }
							  msg = "Product has been successfully updated";
						  } 
						  pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div></div>");
			}
			else if(action.equals("delete"))
			{
							  msg = "bad";
							  allwearabletechnologys = MySqlDataStoreUtilities.getWearableTechnologys();
							  if(allwearabletechnologys.containsKey(productId)){
								  msg = "good";
								  System.out.print("wearabletechnology");
							  }
								  
						  
							  alllaptops = MySqlDataStoreUtilities.getLaptops();
							  if(alllaptops.containsKey(productId)){
								  msg = "good";
							  }
						  
							  allphones = MySqlDataStoreUtilities.getPhones();
							  if(allphones.containsKey(productId)){
								  msg = "good";
							  }
						  
							  allaccessories = MySqlDataStoreUtilities.getAccessories();
							  if(allaccessories.containsKey(productId)){
								  msg = "good";
							}
								
							  if (msg.equals("good"))
							  {		
								
								  try
								  {  
									System.out.print("delete the prodcut");
									 msg = MySqlDataStoreUtilities.deleteproducts(productId);
								  }
								  catch(Exception e)
								  { 
									msg = "Product cannot be deleted";
								  }
								   msg = "Product has been successfully deleted";
							  }else{
								  msg = "Product not available";
							  }
							  pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
			//pw.print("<a style='font-size: 24px;'>Order</a>");
			pw.print("</h2><div class='entry'>");
			pw.print("<h4 style='color:blue'>"+msg+"</h4>");
			pw.print("</div></div></div></div>");
			}
				
			else if(action.equals("inventory"))
			{
					String inventorytype = request.getParameter("inventoryType");
							System.out.println("Inventory Type="+inventorytype);
					if(inventorytype.equals("productAvailability"))
					{
						// Availbale quantity
						/*MySqlDataStoreUtilities obj = new MySqlDataStoreUtilities();
						List<AvailableProduct> availableproductslist = obj.availablequantity();*/
						/*HashMap<String,Phone> hm1 = new HashMap<String,Phone>();
						try
						{
							hm1=MySqlDataStoreUtilities.availablequantity();
							allphones=MySqlDataStoreUtilities.getPhones();
							alllaptops=MySqlDataStoreUtilities.getLaptops();
							System.out.println("PH SIze"+allphones.size());
							System.out.println("HM SIze"+hm1.size());
						}
						catch(Exception e)
						{
					
						}
						//HashMap<String,AvailableProduct> hm = new HashMap<String,AvailableProduct>();
						pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h1>Available Products</h1>");
						pw.print("<table border='2'><tr><th width='30%'>Product Name</th><th width='30%'>Quantity</th><th width='30%'>Price</th></tr>");
		
						for (Map.Entry<String,Phone> entry : allphones.entrySet()) 
						{
							Phone ap=entry.getValue();
							pw.print("<tr><td><h5>"+ap.getName()+"</h5></td>");
							pw.print("<td><h5>"+ap.getQuantity()+"</h5></td>");
							pw.print("<td><h5> $ "+ap.getPrice()+"</h5></td></tr>");
						}
						for (Map.Entry<String,Laptop> entry : alllaptops.entrySet()) 
						{
							Laptop ap=entry.getValue();
							pw.print("<tr><td><h5>"+ap.getName()+"</h5></td>");
							pw.print("<td><h5>"+ap.getQuantity()+"</h5></td>");
							pw.print("<td><h5> $ "+ap.getPrice()+"</h5></td></tr>");
						}
						pw.print("</table>");
						pw.print("</br>");
						pw.print("</br>"+"</div></div></div>");*/
						
						try
						{
							rs=MySqlDataStoreUtilities.availablequantity();
						}
						catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										pw.print("<h1>Available Products</h1>");
										pw.print("<table border='2'><tr><th width='30%'>Sr.No</th><th width='30%'>Product Name</th><th width='30%'>Product Price</th><th width='30%'>Quantity Available</th></tr>");
										
									int j=1;
									while(rs.next())
									{
										pw.print("<tr><td>"+j+".</td>");
										pw.print("<td><h5>"+rs.getString("productdetails.productName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productdetails.productPrice")+"</h5></td>");
										pw.print("<td><h5>"+rs.getInt("remquantity")+"</h5></td></tr>");
										//pw.print("<td><h5>"+rs.getDouble("productQuantity")-rs.getDouble("productSold")+"</h5></td></tr>");
								
										//pw.print("<tr>");	
											//		pw.print("<td>"+j+".</td>");				
									//	pw.print("<td>"+rs.getString("productName")+"</td>");
										//pw.print("<td>"+rs.getDouble("productPrice")+"</td>");
										/*int x=0;
										x=rs.getInt("originalProductCount")-rs.getInt("soldProductCount");*/
										//pw.print("<td>"+rs.getDouble("productDiscount")+"</td>");
										//pw.print("</tr>");
										j++;
										
									}
									pw.print("</table>");
									pw.print("</br>");
									pw.print("</br>"+"</div></div></div>");
								}
								catch(Exception e){}
							
						}
						
					}
					/*pw.println("<div class=\"col-md-9\"><div id='content'><div class='post'>");
							pw.println("<h2 class='title meta'><a style='font-size: 24px;'>Data Visualization</a></h2>"
									+ "<div class='entry'>");
							pw.println("<script type='text/javascript' src='https://www.gstatic.com/charts/loader.js'></script>");
							pw.println("<div id='chart_div'></div>");
							/*pw.println("<div id='product-bar-chart'></div>");
							pw.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");
							pw.println("<script type='text/javascript' src='visualization-chart-script.js'></script>");
							*/
							
					else if(inventorytype.equals("graphicalRepresenation"))
					{
							try
					{
						rs=MySqlDataStoreUtilities.availablequantity();
						//System.out.println("rs2 size"+rs);
					}
					catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									//pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										//pw.print("<h1>Chart!!!</h1>");
										
										
										pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");

										pw.println("<script type='text/javascript'>");



										// Load the Visualization API and the corechart package.

										pw.println("google.charts.load('current', {'packages':['corechart']});");



										// Set a callback to run when the Google Visualization API is loaded.

										pw.println("google.charts.setOnLoadCallback(drawChart);");



										// Callback that creates and populates a data table,

										// instantiates the pie chart, passes in the data and

										// draws it.

										pw.println("function drawChart() {");



										// Create the data table.

										pw.println("var data = new google.visualization.DataTable();");

										pw.println("data.addColumn('string', 'ProductName');");

										pw.println("data.addColumn('number', 'Quantity');");

										pw.println(" data.addRows([");
										while(rs.next())
										{
											pw.println(" ['" + rs.getString("productdetails.productName") + "', " + rs.getInt("remquantity") + "],");
										/*pw.print("<tr><td>"+j+".</td>");
										pw.print("<td><h5>"+rs.getString("productName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productPrice")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productDiscount")+"</h5></td></tr>");*/
								
										//pw.print("<tr>");	
											//		pw.print("<td>"+j+".</td>");				
									//	pw.print("<td>"+rs.getString("productName")+"</td>");
										//pw.print("<td>"+rs.getDouble("productPrice")+"</td>");
										/*int x=0;
										x=rs.getInt("originalProductCount")-rs.getInt("soldProductCount");*/
										//pw.print("<td>"+rs.getDouble("productDiscount")+"</td>");
										//pw.print("</tr>");
										//j++;
										}
										pw.println("]);");

										// Set chart options

										pw.println(" var options = {'title':'ProductName',");

										pw.println("        'width':900,");

										pw.println("       'height':5000};");



										// Instantiate and draw our chart, passing in some options.

										pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");

										pw.println("  chart.draw(data, options);     }");

										pw.println(" </script>");
																		
										pw.print("<div class=\"col-md-9\"><div id='content'>");

										pw.print("<div class='post'>");

										pw.print("<h3 class='title'>");

										pw.print("Graphical Representation of the availability of the items in the store");

										pw.print("</h3>");

										pw.print("<div class='entry'>");

										pw.println("<div id='chart_div'></div>");



										pw.print("</div></div></div>");


								}
								catch(Exception e){}
				
						}
							
					}
					else if(inventorytype.equals("discountedProducts"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							System.out.println("Inventory Type="+inventorytype);
						// Availbale quantity
						/*MySqlDataStoreUtilities obj = new MySqlDataStoreUtilities();
						List<AvailableProduct> availableproductslist = obj.availablequantity();*/
						//HashMap<String,Phone> hm1 = new HashMap<String,Phone>();
						/*try
						{
							alldiscountphones=MySqlDataStoreUtilities.getDiscountedproductnames();
							//HashMap<String,Phone> hm2 = new HashMap<String,Phone>();
							
							allphones=MySqlDataStoreUtilities.getPhones();
							alllaptops=MySqlDataStoreUtilities.getLaptops();
							System.out.println("PH SIze"+allphones.size());
							System.out.println("HM SIze"+alldiscountphones.size());
						}
						catch(Exception e)
						{
					
						}
						//HashMap<String,AvailableProduct> hm = new HashMap<String,AvailableProduct>();
						pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h1>Available Products</h1>");
						pw.print("<table border='2'><tr><th width='30%'>Product Name</th><th width='30%'>Quantity</th><th width='30%'>Price</th></tr>");
		
						for (Map.Entry<String,Phone> entry : allphones.entrySet()) 
						{
							Phone ap=entry.getValue();
							pw.print("<tr><td><h5>"+ap.getName()+"</h5></td>");
							pw.print("<td><h5>"+ap.getQuantity()+"</h5></td>");
							pw.print("<td><h5> $ "+ap.getPrice()+"</h5></td></tr>");
						}
						for (Map.Entry<String,Laptop> entry : alllaptops.entrySet()) 
						{
							Laptop ap=entry.getValue();
							pw.print("<tr><td><h5>"+ap.getName()+"</h5></td>");
							pw.print("<td><h5>"+ap.getQuantity()+"</h5></td>");
							pw.print("<td><h5> $ "+ap.getPrice()+"</h5></td></tr>");
						}
						pw.print("</table>");
						pw.print("</br>");
						pw.print("</br>"+"</div></div></div>");
					*/
					try
					{
						rs=MySqlDataStoreUtilities.getDiscountedproductnames();
						//System.out.println("rs2 size"+rs);
					}
					catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										pw.print("<h1>Products on Sale!!!</h1>");
										pw.print("<table border='2'><tr><th width='30%'>Sr.No</th><th width='30%'>Product Name</th><th width='30%'>Product Price</th><th width='30%'>Discount</th></tr>");
										
									int j=1;
									while(rs.next())
									{
										pw.print("<tr><td>"+j+".</td>");
										pw.print("<td><h5>"+rs.getString("productName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productPrice")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productDiscount")+"</h5></td></tr>");
								
										//pw.print("<tr>");	
											//		pw.print("<td>"+j+".</td>");				
									//	pw.print("<td>"+rs.getString("productName")+"</td>");
										//pw.print("<td>"+rs.getDouble("productPrice")+"</td>");
										/*int x=0;
										x=rs.getInt("originalProductCount")-rs.getInt("soldProductCount");*/
										//pw.print("<td>"+rs.getDouble("productDiscount")+"</td>");
										//pw.print("</tr>");
										j++;
										
									}
									pw.print("</table>");
						pw.print("</br>");
						pw.print("</br>"+"</div></div></div>");
								}
								catch(Exception e){}
				
						}
					}
					else if(inventorytype.equals("manufacturerRebateProducts"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							System.out.println("Inventory Type="+inventorytype);
						
					try
					{
						rs=MySqlDataStoreUtilities.getRebatedItems();
						//System.out.println("rs size"+rs1);
					}
					catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										pw.print("<h1>Products on Rebate!!!</h1>");
										pw.print("<table border='2'><tr><th width='30%'>Sr.No</th><th width='30%'>Product Name</th><th width='30%'>Product Rebate</th></tr>");
									//System.out.println("RS====="+rs1.getDouble("productRebate"));
									int m=1;
									while(rs.next())
									{
										pw.print("<tr><td>"+m+".</td>");
										pw.print("<td><h5>"+rs.getString("productName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productRebate")+"</h5></td></tr>");
										
										m++;
										
									}
									pw.print("</table>");
									pw.print("</br>");
									pw.print("</br>"+"</div></div></div>");
								}
								catch(Exception e){}
				
						}
					}
					else{
						System.out.println("HI");
					}
				
			}					
			else if(action.equals("sales")) 
			{
				String saletype=request.getParameter("saleType");
				System.out.println("Sale Type="+saletype);
				if(saletype.equals("productSold"))
					{
						
						try
						{
							rs=MySqlDataStoreUtilities.getProductsold(/*orderName*//*,orderPrice,count1,totsales*/);
						}
						catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										pw.print("<h1>Products Sold</h1>");
										pw.print("<table border='2'><tr><th width='30%'>Sr.No</th><th width='30%'>Product Name</th><th width='30%'>Product Price</th><th width='30%'># of Products Sold</th><th width='30%'>Total Sales Made</th></tr>");
										
									int k=1;
									while(rs.next())
									{
										pw.print("<tr><td>"+k+".</td>");
										pw.print("<td><h5>"+rs.getString("orderName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("orderPrice")+"</h5></td>");
										pw.print("<td><h5>"+rs.getInt("count1")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("totsales")+"</h5></td></tr>");
										
										k++;
										
									}
									pw.print("</table>");
									pw.print("</br>");
									pw.print("</br>"+"</div></div></div>");
								}
								catch(Exception e){}
							
						}
						
					}
					else if(saletype.equals("graphicalRepresenation"))
					{
						/*out.println("<div id='product-bar-chart'></div>");
		out.println("<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js'></script>"+
		"<script type='text/javascript' src='https://www.google.com/jsapi'></script>");
		out.println("<script type='text/javascript' src='SalesBarChart.js'></script>");*/
						try
					{
						rs=MySqlDataStoreUtilities.getProductsold();
						//System.out.println("rs2 size"+rs);
					}
					catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									//pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										//pw.print("<h1>Chart!!!</h1>");
										
										
										pw.println("<script type='text/javascript' src=\"https://www.gstatic.com/charts/loader.js\"></script>");

										pw.println("<script type='text/javascript'>");



										// Load the Visualization API and the corechart package.

										pw.println("google.charts.load('current', {'packages':['corechart']});");



										// Set a callback to run when the Google Visualization API is loaded.

										pw.println("google.charts.setOnLoadCallback(drawChart);");



										// Callback that creates and populates a data table,

										// instantiates the pie chart, passes in the data and

										// draws it.

										pw.println("function drawChart() {");



										// Create the data table.

										pw.println("var data = new google.visualization.DataTable();");

										pw.println("data.addColumn('string', 'ProductName');");

										pw.println("data.addColumn('number', 'TotalSales');");

										pw.println(" data.addRows([");
										/*pw.print("<td><h5>"+rs.getString("orderName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("orderPrice")+"</h5></td>");
										pw.print("<td><h5>"+rs.getInt("count1")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("totsales")+"</h5></td></tr>");*/
										while(rs.next())
										{
											pw.println(" ['" + rs.getString("orderName") + "', " + rs.getDouble("totsales")  + "],");
										/*pw.print("<tr><td>"+j+".</td>");
										pw.print("<td><h5>"+rs.getString("productName")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productPrice")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("productDiscount")+"</h5></td></tr>");*/
								
										//pw.print("<tr>");	
											//		pw.print("<td>"+j+".</td>");				
									//	pw.print("<td>"+rs.getString("productName")+"</td>");
										//pw.print("<td>"+rs.getDouble("productPrice")+"</td>");
										/*int x=0;
										x=rs.getInt("originalProductCount")-rs.getInt("soldProductCount");*/
										//pw.print("<td>"+rs.getDouble("productDiscount")+"</td>");
										//pw.print("</tr>");
										//j++;
										}
										pw.println("]);");

										// Set chart options

										pw.println(" var options = {'title':'ProductName',");

										pw.println("        'width':900,");

										pw.println("       'height':1200};");



										// Instantiate and draw our chart, passing in some options.

										pw.println(" var chart = new google.visualization.BarChart(document.getElementById('chart_div'));");

										pw.println("  chart.draw(data, options);     }");

										pw.println(" </script>");
																		
										pw.print("<div class=\"col-md-9\"><div id='content'>");

										pw.print("<div class='post'>");

										pw.print("<h3 class='title'>");

										pw.print("Graphical Representation of the total sale made");

										pw.print("</h3>");

										pw.print("<div class='entry'>");

										pw.println("<div id='chart_div'></div>");



										pw.print("</div></div></div>");


								}
								catch(Exception e){}
				
						}
					}
					
					else if(saletype.equals("dailySales"))
					{
						
						try
						{
							rs=MySqlDataStoreUtilities.getTotdailysale(/*orderName*//*,orderPrice,count1,totsales*/);
						}
						catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										pw.print("<h1>Daily Sales Report</h1>");
										pw.print("<table border='2'><tr><th width='30%'>Sr.No</th><th width='30%'>Date on which Sale made</th><th width='30%'>Total Sales on that date</th></tr>");
										
									int k=1;
									while(rs.next())
									{
										pw.print("<tr><td>"+k+".</td>");
										pw.print("<td><h5>"+rs.getString("orderDate")+"</h5></td>");
										pw.print("<td><h5>"+rs.getDouble("totsales")+"</h5></td></tr>");
										
										k++;
										
									}
									pw.print("</table>");
									pw.print("</br>");
									pw.print("</br>"+"</div></div></div>");
								}
								catch(Exception e){}
							
						}
						
					}
					


			}
			else if(action.equals("choropleth"))
			{
				String cmtype = request.getParameter("cmtype");
				if(cmtype.equals("pr"))
					{
						pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
							
							utility.printHtml("US-map4.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("mma1"))
					{
							
						pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
						
							
							utility.printHtml("US-map.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("pb"))
					{
						//String inventorytype = request.getParameter("inventoryType");
						//String cmtype = request.getParameter("cmtype");
							pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
						/*try
						{
						rs=MySqlDataStoreUtilities.getsumbystate();
						//System.out.println("rs size"+rs1);
						}
						catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{
								try
								{
									pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
										pw.print("<h1>CMMMM</h1>");
										pw.print("<table border='2'><tr><th width='30%'>state</th><th width='30%'>Total Sales on that date</th></tr>");
										ArrayList<String> list=new ArrayList<String>();
									while(rs.next())
										{
											pw.println(" ['" + rs.getString("state") + "', " + rs.getDouble("Totsales")  + "],");
											String val=rs.getString("state")+","+rs.getDouble("Totsales");
											list.add(val);
											String csv=list.toString();
											String csvWithQuote = list.toString().replace("[", "['").replace("]", "']").replace(", ", "','");
										}
										pw.println("]);");
										System.out.println("LIST "+list);
										pw.print("</table>");
									pw.print("</br>");
									pw.print("</br>"+"</div></div></div>");
									
									
								}
								catch(Exception e){}
				
						}*/
						utility.printHtml("US-map1.htm");
							//pw.print("</div></div></div></div>");
					}	
					else if(cmtype.equals("min"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							//String cmtype = request.getParameter("cmtype");
							pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
							
							utility.printHtml("US-mapmin.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("max"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							//String cmtype = request.getParameter("cmtype");
							pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
							
							utility.printHtml("US-mapmax.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("avg"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							//String cmtype = request.getParameter("cmtype");
							pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
							
							utility.printHtml("US-mapavg.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("pr5"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							//String cmtype = request.getParameter("cmtype");
						pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
							
							utility.printHtml("US-map5.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("avgsp"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							//String cmtype = request.getParameter("cmtype");
							pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
							
							utility.printHtml("US-map2.htm");
							pw.print("</div></div></div></div>");
						
					}
					else if(cmtype.equals("totsp"))
					{
						//String inventorytype = request.getParameter("inventoryType");
							//String cmtype = request.getParameter("cmtype");
							pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
						pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
						+ "<div class='entry'>");
						/*try
						{
						rs=MySqlDataStoreUtilities.getsumbystate();
						//System.out.println("rs size"+rs1);
						}
						catch(Exception e){}
						if(rs==null)
						{
							pw.println("<h2>MySql server is not up and running</h2>");
							//pw.println("<tr><td><h2>no</td></tr></h2>");
						}
						else
						{*/	
							ArrayList<String> orders;
							//ResultSet orders=null;
							orders=MySqlDataStoreUtilities.getsumbystate();
							Gson gson=new Gson();
							String jsonString =gson.toJson(orders);
							System.out.println(jsonString + "\n\n\n");
							FileWriter fileWriter = null;
							try 
							{
							JSONArray docs = new JSONArray(jsonString);
							String fileName = "C:\\tomcat-7.0.34-preconfigured\\apache-tomcat-7.0.34\\webapps\\smt\\stateslived.csv";
							fileWriter = new FileWriter(fileName);
							fileWriter.append(FILE_HEADER.toString());
							fileWriter.append(NEW_LINE_SEPARATOR);

								for(int i = 0; i < docs.length(); i++){
									JSONObject line = docs.getJSONObject(i);
									fileWriter.append(String.valueOf(line.get("orderPrice")));
									fileWriter.append(COMMA_DELIMITER);
									fileWriter.append(line.getString("state"));
									fileWriter.append(NEW_LINE_SEPARATOR);			
								}
							}
							catch (JSONException e) 
							{
								e.printStackTrace();
							}
							catch(IOException e)
							{
							// TODO Auto-generated catch block
								e.printStackTrace();
							}finally{
								try {
									fileWriter.flush();
									fileWriter.close();
								} catch (IOException e) {
									System.out.println("Error while flushing/closing fileWriter !!!");
									e.printStackTrace();
								}
							}
								
							
							utility.printHtml("US-map3.htm");
							//pw.print("</div></div></div></div>");
						
					//}
					
			}
			}			
utility.printHtml("Footer.html");
			}			
}