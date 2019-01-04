

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet("/WriteReview")
	//once the user clicks writereview button from products page he will be directed
 	//to write review page where he can provide reqview for item rating reviewtext	
	
public class WriteReview extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
	        Utilities utility= new Utilities(request, pw);
		review(request, response);
	}
	
	protected void review(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        try
                {
                   
                response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
                Utilities utility = new Utilities(request,pw);
		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to Write a Review");
			response.sendRedirect("Login");
			return;
		}
                String productname=request.getParameter("name");		
                String producttype=request.getParameter("type");
                String productmaker=request.getParameter("creator");
		//String image = request.getParameter("image");
		double price = Double.parseDouble(request.getParameter("price"));
		//String color = request.getParameter("color");
		String condition = request.getParameter("condition");
		String manufacturer = request.getParameter("company");
		String retailer = request.getParameter("retailer");
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("username");
		String state=request.getParameter("state");
		
		String productonsale = "Yes";
		
		String manufacturerrebate = "No";
		//String emailId = userId;
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date today = new Date();
		String reviewdate = dateFormat.format(today);
			      
      // on filling the form and clicking submit button user will be directed to submit review page
                utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
		pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
                pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
                pw.print("<table class='gridtable'>");
		pw.print("<tr><td> Product Name: </td><td>");
		pw.print(productname);
		pw.print("<input type='hidden' name='productname' value='"+productname+"'>");
		pw.print("</td></tr>");
	        pw.print("<tr><td> Product Type:</td><td>");
                pw.print(producttype);
	        pw.print("<input type='hidden' name='producttype' value='"+producttype+"'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td> Product Price:</td><td>");
                pw.print(price);
	        pw.print("<input type='hidden' name='price' value='"+price+"'>");
		pw.print("</td></tr>");
		
		
	    
                pw.print("<tr><td> Manufacturer Name: </td><td>");
                pw.print(productmaker);
		pw.print("<input type='hidden' name='productmaker' value='"+productmaker+"'>");
                pw.print("</td></tr>");
		pw.print("<tr><td> Manufacturer Rebate:</td><td>");
                pw.print(manufacturerrebate);
				pw.print("<input type='hidden' name='manufacturerrebate' value='"+manufacturerrebate+"'>");
				pw.print("</td></tr>");
		
				pw.print("<tr><td> Condition: </td><td>");
				pw.print(condition);
		pw.print("<input type='hidden' name='condition' value='"+condition+"'>");
                pw.print("</td></tr>");
				pw.print("<tr><td> UserId:</td><td>");
                pw.print(userid);
	        pw.print("<input type='hidden' name='userid' value='"+userid+"'>");
		pw.print("</td></tr>");
				
				
		
		pw.print("<tr><td> Product on Sale:</td><td>");
                pw.print(productonsale);
	        pw.print("<input type='hidden' name='productonsale' value='"+productonsale+"'>");
		pw.print("</td></tr>");
		
		pw.print("<tr><td> Review Date:</td><td>");
                pw.print(reviewdate);
	        pw.print("<input type='hidden' name='reviewdate' value='"+reviewdate+"'>");
		pw.print("</td></tr><table>");
		
		
		
  				pw.print("<table><tr></tr><tr></tr><tr><td> Review Rating: </td>");
					pw.print("<td>");
						pw.print("<select name='reviewrating'>");
						pw.print("<option value='1' selected>1</option>");
						pw.print("<option value='2'>2</option>");
						pw.print("<option value='3'>3</option>");
						pw.print("<option value='4'>4</option>");
						pw.print("<option value='5'>5</option>");  
					pw.print("</td></tr>");
				
				
					/*pw.print("<tr>");
					pw.print("<td> Review Date: </td>");
					pw.print("<td> <input type='date' name='reviewdate'required> </td>");
			        pw.print("</tr>");	
					pw.print("<tr>");
					*/
					pw.print("<tr>");
					pw.print("<td> RetailerName: </td>");
					pw.print("<td><textarea rows='4' cols='50' name='retailername' required> </textarea></td></tr>");
					
					pw.print("<tr>");
					pw.print("<td> Retailer State: </td>");
					pw.print("<td> <input type='text' name='retailerstate' maxlength='10' required> </td>");
					pw.print("</tr>");
					
					pw.print("<tr>");
					pw.print("<td> Retailer City: </td>");
					pw.print("<td> <input type='text' name='retailercity' maxlength='10' required> </td>");
					pw.print("</tr>");
					
					pw.print("<td> Retailer Zip: </td>");
					pw.print("<td> <input type='text' name='retailerpin' maxlength='5' required> </td>");
					pw.print("</tr>");					
					
					/*pw.print("<tr>");
					pw.print("<td> ProductOnSale: </td>");
					pw.print("<td> <input type='text' name='productonsale' maxlength='20' required> </td>");
					pw.print("</tr>");
					
					pw.print("<tr>");
					pw.print("<td> User Id: </td>");
					pw.print("<td> <input type='text' name='userid' maxlength='10' required> </td>");
					pw.print("</tr>");*/
					
					pw.print("<tr>");
					pw.print("<td> User age: </td>");
					pw.print("<td> <input type='text' name='age' maxlength='10' required> </td>");
					pw.print("</tr>");
					
					pw.print("<tr>");
					pw.print("<td> Gender: </td>");
					pw.print("<td> <input type='text' name='gender' maxlength='10' required> </td>");
					pw.print("</tr>");
					pw.print("<tr>");
					pw.print("<td> Occupation: </td>");
					pw.print("<td> <input type='text' name='occupation' maxlength='20' required> </td>");
					pw.print("</tr>");
					
					pw.print("<tr>");
					pw.print("<td> State: </td>");
					pw.print("<td> <input type='text' name='state' maxlength='20' required> </td>");
					pw.print("</tr>");
					
					pw.print("<tr>");
					/*pw.print("<td> Manufacturer Rebate: </td>");
					pw.print("<td> <input type='text' name='discount' maxlength='5' required> </td>");
					pw.print("</tr>");*/
					
					pw.print("<tr>");
					pw.print("<td> Review Text: </td>");
					pw.print("<td><textarea rows='4' cols='50' name='reviewtext' required> </textarea></td></tr>");
					
				pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");

                pw.print("</h2></div></div></div></div>");		
		utility.printHtml("Footer.html");
	                     	
                    }
              	catch(Exception e)
		{
                 System.out.println(e.getMessage());
		}  			
       
	 	
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		 
		
            }
}
