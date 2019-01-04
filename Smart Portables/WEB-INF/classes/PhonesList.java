import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PhonesList")

public class PhonesList extends HttpServlet {

	/* Phones Page Displays all the Phones and their Information in Phone Speed */

	//private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* Checks the Phones type whether it is electronicArts or activision or takeTwoInteractive */
				
		
		//System.out.println("========================cate===========");
		HashMap<String,Phone> allphones = new HashMap<String,Phone> ();
		try{
		     allphones = MySqlDataStoreUtilities.getPhones();
			 System.out.println("Phoneize"+allphones.size());
		}
		catch(Exception e)
		{
			
		}
		String name = null;
		String CategoryName = request.getParameter("creator");
		HashMap<String, Phone> hm = new HashMap<String, Phone>();
		if(CategoryName==null)
		{
			hm.putAll(allphones);
			name = "";
		}
		else
		{
		  if(CategoryName.equals("apple"))
		  {
					//System.out.println("========================Before for loop saxparser===========");
			for(Map.Entry<String,Phone> entry : allphones.entrySet())
				{
					//System.out.println("========================saxparser===========");
				if(entry.getValue().getRetailer().equals("Apple"))

				 {
					//System.out.println("========================inside===========");
					hm.put(entry.getValue().getId(),entry.getValue());
				 }
				}
			name = "Apple";
		  }
		  else if(CategoryName.equals("samsung"))
		  {
			for(Map.Entry<String,Phone> entry : allphones.entrySet())
				{
				if(entry.getValue().getRetailer().equals("Samsung"))
				 {
					 hm.put(entry.getValue().getId(),entry.getValue());
					 /*name="samsung";
					 System.out.println("MN"+name);*/
				 }
				}	
			name = "Samsung";
			//System.out.println("MN"+name);
		  }
		  else if(CategoryName.equals("htc"))
		  {
			for(Map.Entry<String,Phone> entry : allphones.entrySet())
				{
				if(entry.getValue().getRetailer().equals("HTC"))
				{
					 hm.put(entry.getValue().getId(),entry.getValue());
				}
				}
			name = "HTC";
		  }
		}

		/* Header, Left Navigation Bar are Printed.

		All the Phones and Phones information are dispalyed in the Content Section

		and then Footer is Printed*/
		
		Utilities utility = new Utilities(request,pw);
		utility.printHtml("Header.html");
		System.out.println("MN"+name);
		utility.printHtml("Left_Nav.html");
	
		pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>"+name+" Phones</a>");
		//System.out.println("========================vif===========");
		pw.print("</h2><div class='entry'><table id='bestseller'>");
		//System.out.println("========================sfter best seller===========");
		int i = 1; int size= hm.size();
		System.out.println("HM PHONE size"+hm.size());
		//System.out.println("Manuuuuu"+name);
		for(Map.Entry<String, Phone> entry : hm.entrySet()){
			Phone phone = entry.getValue();
			if(i%3==1) pw.print("<tr>");
			pw.print("<td><div id='shop_item'>");
			pw.print("<h3>"+phone.getName()+"</h3>");
			pw.print("<h3>"+phone.getCondition()+"</h3>");
			pw.print("<strong>"+ "$" + phone.getPrice() + "</strong><ul>");
			pw.print("<li id='item'><img src='images/phones/"+phone.getImage()+"' height=\"300\" alt='' class = \"img-responsive\" /></li>");
			
			pw.print("<li><form method='post' action='ViewCart'>" +
					"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='condition' value='"+phone.getCondition()+"'>"+
					"<input type='hidden' name='access' value=''>"+
					"<input type='submit' class='btnbuy' value='BuyNow'></form></li>");
			pw.print("<li><form method='post' action='WriteReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='condition' value='"+phone.getCondition()+"'>"+
					"<input type='hidden' name='price' value='"+phone.getPrice()+"'>"+
					"<input type='hidden' name='access' value=''>"+
				    "<input type='submit' value='WriteReview' class='btnreview'></form></li>");
			pw.print("<li><form method='post' action='ViewReview'>"+"<input type='hidden' name='name' value='"+entry.getKey()+"'>"+
					"<input type='hidden' name='type' value='phones'>"+
					"<input type='hidden' name='creator' value='"+CategoryName+"'>"+
					"<input type='hidden' name='condition' value='"+phone.getCondition()+"'>"+
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
