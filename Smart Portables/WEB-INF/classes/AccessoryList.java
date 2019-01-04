import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AccessoryList")

public class AccessoryList extends HttpServlet {

	/* Accessory Page Displays all the Accessories and their Information in Smart Tech */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String name = null;
		String CategoryName = request.getParameter("creator");
		String ProductName = request.getParameter("access");

		HashMap<String,Accessory> allaccessories = new HashMap<String,Accessory> ();
        HashMap<String,Laptop> alllaptops = new HashMap<String,Laptop> ();

		
		try{
		     alllaptops = MySqlDataStoreUtilities.getLaptops();
		}
		catch(Exception e)
		{
			
		} 

		
		try{
		     allaccessories = MySqlDataStoreUtilities.getAccessories();
		}
		catch(Exception e)
		{
			
		}
		
		/* Checks the laptops type whether it is DELL, HP or Apple */

		HashMap<String, Laptop> hm = new HashMap<String, Laptop>();
		
			if(CategoryName.equals("dell"))
		    {
				System.out.println("------------------------------------------2.-------------------------------------------");
		
			for(Map.Entry<String,Laptop> entry : alllaptops.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("DELL"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
					 System.out.print(entry.getValue().getAccessories().size());
				 }
				}
				
			}
			else if(CategoryName.equals("hp"))
			{
				System.out.println("------------------------------------------9.-------------------------------------------");
				for(Map.Entry<String,Laptop> entry : alllaptops.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("HP"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 
			}
			else if(CategoryName.equals("apple"))
			{
				System.out.println("------------------------------------------8.-------------------------------------------");
				for(Map.Entry<String,Laptop> entry : alllaptops.entrySet())
				{
					if(entry.getValue().getRetailer().equals("Apple"))
					{
						System.out.println(entry.getValue().getId() + ":::" + entry.getValue().getImage() +":::"+ entry.getValue().getPrice());
						hm.put(entry.getValue().getId(),entry.getValue());
					}
				}
			
			}
		
			
//		Laptop laptop = hm.get(LaptopName);
				
		/* Header, Left Navigation Bar are Printed.

		All the Accessories and Accessories information are dispalyed in the Content Section

		and then Footer is Printed*/

		
		Utilities utility = new Utilities(request,pw);
		//System.out.println("------------------------------------------3.-------------------------------------------");
		
		utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
		pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+ CategoryName +": Accessories</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= 2;
		//System.out.println("------------------------------------------4.-------------------------------------------");
		
		for(Map.Entry<String, Laptop> entry : hm.entrySet())
		{
			//System.out.println("------------------------------------------5.-------------------------------------------");
		
			Laptop laptop = entry.getValue();
			//System.out.println("-----------------------6-------------------");
			System.out.println(laptop.getId() + ":::" + laptop.getImage() +":::"+ laptop.getPrice());

			
			for(Map.Entry<String, String> acc:laptop.getAccessories().entrySet())
			{
		        //System.out.println("------------------------------------------7.-------------------------------------------");
		
				Accessory accessory= allaccessories.get(acc.getValue());
				if(i%2==1) pw.print("<tr>");
				System.out.print(size);
				pw.print("<td><div id='shop_item'>");
				pw.print("<h3>"+accessory.getName()+"</h3>");
				pw.print("<strong>"+accessory.getPrice()+"$</strong><ul>");
				pw.print("<li id='item'><img src='images/accessories/"+accessory.getImage()+"' alt=''  class = \"img-responsive\"  /></li>");
				pw.print("<li><form method='post' action='ViewCart'>" +
						"<input type='hidden' name='name' value='"+acc.getValue()+"'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
						"<input type='hidden' name='condition' value='"+laptop.getCondition()+"'>"+
						"<input type='hidden' name='access' value='"+laptop.getName()+"'>"+
						"<input type='submit' class='btnbuy' value='BuyNow'></form></li>");
				pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+accessory.getName()+"'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
						"<input type='hidden' name='condition' value='"+laptop.getCondition()+"'>"+
						"<input type='hidden' name='access' value='"+laptop.getName()+"'>"+
						"<input type='submit' value='WriteReview' class='btnreview'></form></li>");
				pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+accessory.getName()+"'>"+
						"<input type='hidden' name='type' value='accessories'>"+
						"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
						"<input type='hidden' name='condition' value='"+laptop.getCondition()+"'>"+
						"<input type='hidden' name='access' value='"+laptop.getName()+"'>"+
						"<input type='submit' value='ViewReview' class='btnreview'></form></li>");
		
				pw.print("</ul></div></td>");
				if(i%2==0 || i == size) pw.print("</tr>");
				i++;
			
			}	
		}	
		pw.print("</table></div></div></div></div>");		
		utility.printHtml("Footer.html");
	}
}
