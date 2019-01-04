

import java.io.IOException;
import java.io.PrintWriter;
import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@WebServlet("/ViewReview")

public class ViewReview extends HttpServlet {

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
			session.setAttribute("login_msg", "Please Login to view Review");
			response.sendRedirect("Login");
			return;
		}
		 String productname=request.getParameter("name");		 
		HashMap<String, ArrayList<Review>> hm= MongoDBDataStoreUtilities.selectReview();
		String username = "";
		String reviewrating = "";
		String reviewdate;
		String reviewtext = "";	
		String retailerpin = "";
		String retailercity="";
		String retailerstate="";
		String productprice="";
		String retailername="";	
		String state="";
			System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
                utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
	
                pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
			
			//if there are no reviews for product print no review else iterate over all the reviews using cursor and print the reviews in a table
		if(hm==null)
		{
		pw.println("<h2>Mongo Db server is not up and running</h2>");
		}
		else
		{
                if(!hm.containsKey(productname)){
				pw.println("<h2>There are no reviews for this product.</h2>");
			}else{
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
		for (Review r : hm.get(productname)) 
				 {		
			 System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
		pw.print("<table class='gridtable'>");
				pw.print("<tr>");
				pw.print("<td> Product Name: </td>");
				productname = r.getProductName();
				pw.print("<td>" +productname+ "</td>");
				pw.print("</tr>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				
				pw.print("<tr>");
				pw.print("<td> userID: </td>");
				username = r.getUserName();
				pw.print("<td>" +username+ "</td>");
				pw.print("</tr>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				
				pw.println("<tr>");
				pw.println("<td> Review Rating: </td>");
				reviewrating = r.getReviewRating().toString();
				pw.print("<td>" +reviewrating+ "</td>");
				pw.print("</tr>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				
				pw.println("<tr>");
				pw.println("<td> State: </td>");
				state = r.getState().toString();
				pw.print("<td>" +state+ "</td>");
				pw.print("</tr>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				
				pw.print("<tr>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				pw.print("<td> Review Date: </td>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				reviewdate = r.getReviewDate().toString();
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				pw.print("<td>" +reviewdate+ "</td>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				pw.print("</tr>");
				System.out.println("JELlllllllllllll**********llllllllllllllllllllllllll");
				pw.print("<tr>");
				pw.print("<td> Retailer Name: </td>");
				retailername = r.getRetailerName();
				pw.print("<td>" +retailername+ "</td>");
				pw.print("</tr>");
				
				pw.print("<tr>");
				pw.print("<td> Retailer Zip: </td>");
				retailerpin = r.getRetailerPin().toString();
				pw.print("<td>" +retailerpin+ "</td>");
				pw.print("</tr>");	
				pw.print("<tr>");
				pw.print("<td> Review Text: </td>");
				reviewtext = r.getReviewText();
				pw.print("<td>" +reviewtext+ "</td>");
				pw.print("</tr>");
				pw.print("                                                                                                                                     ");
				pw.print("<tr>");
				pw.println("</table>");
			pw.print("<div>");
				pw.print("</div>");
				System.out.println("JELlllllllllllllllllllllllllllllllllllllll");
				}					
							
		}
		}	       
                pw.print("</div></div></div></div>");		
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
