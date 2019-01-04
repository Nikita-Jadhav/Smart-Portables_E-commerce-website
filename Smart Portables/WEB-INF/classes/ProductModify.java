import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
		if(action.equals("Addproduct"))
		{
			
			
			pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Product</a></h2>"
					+ "<div class='entry'>");
				
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='wearabletechnology' selected>WearableTechnology</option><option value='laptops'>Laptops</option><option value='phones'>Phones</option><option value='accessories'>Accessory</option></select>"
					+ "</td></tr><tr><td>"
					+"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Warranty</h3></td><td><input type='int' step='any' placeholder='please enter numeric data' name='productWarranty' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Quantity</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productQuantity' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Rebate</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productRebate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					/*+ "<h3>Product OrderDate</h3></td><td><input type='text' name='productOrderdate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Delivery Date</h3></td><td><input type='text' name='productDeliverydate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"*/
					+ "<input type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div></div>");
			
		
		
		}else if (action.equals("Updateproduct"))
		{
		     pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Product</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='wearabletechnology' selected>WearableTechnology</option><option value='laptops'>Laptops</option><option value='phones'>Phones</option><option value='accessories'>Accessory</option></select>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Price</h3></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Discount</h3></td><td><input type='number' step='any' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Warranty</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productWarranty' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Quantity</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productQuantity' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Rebate</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productRebate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					
					/*+ "<h3>Product OrderDate</h3></td><td><input type='text' name='productOrderdate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Delivery Date</h3></td><td><input type='text' name='productDeliverydate' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"*/
					+ "<input type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div></div>");	
		}else if(action.equals("Deleteproduct"))
		{
			pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Delete Product</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>ProductId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div></div>");
		}
		else if(action.equals("Inventoryreport"))
		{
			pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Inventory Report</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					//+ "<table style='width:100%'><tr><td>"
					//+ "<li class='dropdown main-menu-bar'><a href='javascript:void(0)' class='dropdown-toggle'><span class='glyphicon'>InventoryReport</span></a><ul class=dropdown-menu dropdown-second-menu><li><a href='#'>Products Availability</a></li><li><a href='#'>Graphical Representation(Availability)</a></li><li><a href='#'>Products On Sale</a></li><li><a href='#'>Products with Manufacturer Rebates</a></li></ul></li>"
					+ "<table><tr><td></td><td>"
					+ "<select name='inventoryType' required>"
					+ "<option value='productAvailability' >Products Availability</option><option value='graphicalRepresenation' >Graphical Representation</option><option value='discountedProducts'>Products with Discount</option><option value='manufacturerRebateProducts'>Products with Manufacturer Rebates</option></select></td><td></td><td></td><td></td><td></td></tr></table>"
					+ "<input type='submit' class='btnbuy' name='button' value='inventory'>"
					+ "</form>" + "</div></div></div></div>");
		}
		else if(action.equals("Dataexplorationutility"))
		{
			pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Choropleth Map</a></h2>"
					+ "<div class='entry'>");
			//utility.printHtml("US-map.htm");
			pw.print("<form method='get' action='ProductCrud'>"
					//+ "<table style='width:100%'><tr><td>"
					//+ "<li class='dropdown main-menu-bar'><a href='javascript:void(0)' class='dropdown-toggle'><span class='glyphicon'>InventoryReport</span></a><ul class=dropdown-menu dropdown-second-menu><li><a href='#'>Products Availability</a></li><li><a href='#'>Graphical Representation(Availability)</a></li><li><a href='#'>Products On Sale</a></li><li><a href='#'>Products with Manufacturer Rebates</a></li></ul></li>"
					+ "<table><tr><td></td><td>"
					+ "<select name='cmtype' required>"
					+ "<option value='pr' >Products Reviewed/State</option>"
					//+ "<option value='mma1' >Min,Max,Avg Rating for Products reviewed</option>"
					+ "<option value='pb'>Products bought/State</option>"
					+ "<option value='min'>Min Rating for Products reviewed</option>"
					+ "<option value='max'>Max Rating for Products reviewed</option>"
					+ "<option value='avg'>Avg Rating for Products reviewed</option>"
					+ "<option value='pr5'>Products reviewed with Rating 5</option>"
					+ "<option value='avgsp'>Avg Product price for sold products</option>"
					+ "<option value='totsp'>Total Product price for sold products</option>"
					+ "</select></td><td></td><td></td><td></td><td></td></tr></table>"
					+ "<input type='submit' class='btnbuy' name='button' value='choropleth'>"
					+ "</form>" + "</div></div></div></div>");
		}
		else 
		{
			pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Sales Report</a></h2>"
					+ "<div class='entry'>");
			
			pw.print("<form method='get' action='ProductCrud'>"
					/*+ "<table style='width:100%'><tr><td>"
					+ "<h3>ProductId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='sales' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"*/
					
					+ "<table><tr><td></td><td>"
					//+ "<h3>OrderName</h3></td><td><input type='text' name='orderName' value='' class='input' required></input>"
					//+ "</td></tr><tr><td>"
					
					+ "<select name='saleType' required>"
					+ "<option value='productSold' >Products Sold</option><option value='graphicalRepresenation' >Graphical Representation</option><option value='dailySales'>Daily Sales</option></select></td><td></td><td></td><td></td><td></td></tr></table>"
					+ "<input type='submit' class='btnbuy' name='button' value='sales'>"
					+ "</form>" + "</div></div></div></div>");
		}
		//displayLogin(request, response, pw, false);
		utility.printHtml("Footer.html");
		}
	}