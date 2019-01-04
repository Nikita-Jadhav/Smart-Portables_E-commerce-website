import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/WearableTechnologyList")

public class WearableTechnologyList extends HttpServlet {

	/* WearableTechnology Page Displays all the WearableTechnologys and their Information in Smart Tech */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
        HashMap<String,WearableTechnology> allwearabletechnologys = new HashMap<String,WearableTechnology> ();


		/* Checks the Tablets type whether it is microsft or sony or nintendo */
		try{
		     allwearabletechnologys = MySqlDataStoreUtilities.getWearableTechnologys();
			 System.out.println("Csize"+allwearabletechnologys.size());
		}
		catch(Exception e)
		{
			
		}
		

		/* Checks the Wearable Technology type whether it is Fitness Watch or Smart Watch or Headphones o Virtual Reality or Pet Tracker */
String name = null;
		String CategoryName = request.getParameter("creator");
		//String ProductName = request.getParameter("access");
		HashMap<String, WearableTechnology> hm = new HashMap<String, WearableTechnology>();
		if(CategoryName==null){
			hm.putAll(allwearabletechnologys);
			name = "";
		}
		else
		{
		   if(CategoryName.equals("fitnessWatches"))
		   {
			 for(Map.Entry<String,WearableTechnology> entry : allwearabletechnologys.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("FitnessWatches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "FitnessWatches";
		   }
		   else if(CategoryName.equals("smartWatches"))
		    {
			for(Map.Entry<String,WearableTechnology> entry : allwearabletechnologys.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("SmartWatches"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "SmartWatches";
			}
			else if(CategoryName.equals("headphones"))
			{
				for(Map.Entry<String,WearableTechnology> entry : allwearabletechnologys.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Headphones"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Headphones";
			}
			if(CategoryName.equals("petTracker"))
		   {
			 for(Map.Entry<String,WearableTechnology> entry : allwearabletechnologys.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("PetTracker"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "PetTracker";
		   }
		   if(CategoryName.equals("virtualReality"))
		   {
			 for(Map.Entry<String,WearableTechnology> entry : allwearabletechnologys.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("VirtualReality"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "VirtualReality";
		   }
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the WearableTechnology and WearableTechnology information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
		pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" WearableTechnology</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
		System.out.println("Size="+hm.size());
		for(Map.Entry<String, WearableTechnology> entry : hm.entrySet())
		{
			WearableTechnology wearabletechnology = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+wearabletechnology.getName()+"</h3>");
			pw.print("<strong>$"+wearabletechnology.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/wearabletechnology/"+wearabletechnology.getImage()+"' alt='' /></li>");
			
			pw.print("<li><form method='post' action='ViewCart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearabletechnologys'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='BuyNow'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearabletechnologys'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='price' value='"+wearabletechnology.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='wearabletechnologys'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='ViewReview' class='btnreview'></form></li>");
			pw.print("</ul></div></td>");
			if(i%3==0 || i == size) pw.print("</tr>");
			i++;
		}	
		pw.print("</table></div></div></div></div>");
   
		utility.printHtml("Footer.html");
		
	}
}
