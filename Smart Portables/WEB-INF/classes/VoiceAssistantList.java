import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VoiceAssistantList")

public class VoiceAssistantList extends HttpServlet {

	/* VoiceAssistant Page Displays all the VoiceAssistants and their Information in Smart Tech */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
		/* Checks the Tablets type whether it is Siri, Google or Alexa */

		HashMap<String,VoiceAssistant> allvoiceassistants = new HashMap<String,VoiceAssistant> ();
		try{
		     allvoiceassistants = MySqlDataStoreUtilities.getVoiceAssistants();
			 System.out.println("Vsize"+allvoiceassistants.size());
		}
		catch(Exception e)
		{
			
		}
		
		String name = null;
		String CategoryName = request.getParameter("creator");
		//String ProductName = request.getParameter("access");
		HashMap<String, VoiceAssistant> hm = new HashMap<String, VoiceAssistant>();

		if(CategoryName==null){
			System.out.println("inside null part");
			hm.putAll(allvoiceassistants);
			name = "";
			System.out.println("exiting null part");
		}
		else
		{
		   if(CategoryName.equals("siri"))
		   {
			 for(Map.Entry<String,VoiceAssistant> entry : allvoiceassistants.entrySet())
			 {
				if(entry.getValue().getRetailer().equals("Siri"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
			 }
				name = "Siri";
		   }
		   else if(CategoryName.equals("google"))
		    {
			for(Map.Entry<String,VoiceAssistant> entry : allvoiceassistants.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Google"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
				 name = "Google";
			}
			else if(CategoryName.equals("alexa"))
			{
				for(Map.Entry<String,VoiceAssistant> entry : allvoiceassistants.entrySet())
				{
				 if(entry.getValue().getRetailer().equals("Alexa"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			   	 name = "Alexa";
			}
		}

		
		/* Header, Left Navigation Bar are Printed.

		All the VoiceAssistant and VoiceAssistant information are dispalyed in the Content Section

		and then Footer is Printed*/

		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
		pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" VoiceAssistants</a>");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		int i = 1; int size= hm.size();
			System.out.println("***INside for loop" + hm.size());
		for(Map.Entry<String, VoiceAssistant> entry : hm.entrySet())
		{
			VoiceAssistant voiceassistant = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+voiceassistant.getName()+"</h3>");
			pw.print("<strong>$"+voiceassistant.getPrice()+"</strong><ul>");
			pw.print("<li id='item'><img src='images/voiceassistants/"+voiceassistant.getImage()+"' alt='' class = \"img-responsive\"/></li>");
			
			pw.print("<li><form method='post' action='ViewCart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='voiceassistants'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='condition' value='"+voiceassistant.getCondition()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='BuyNow'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='voiceassistants'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='condition' value='"+voiceassistant.getCondition()+"'>"+
					"<input type='hidden' name='price' value='"+voiceassistant.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='voiceassistants'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='condition' value='"+voiceassistant.getCondition()+"'>"+
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
