import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/Payment")

public class Payment extends HttpServlet {
	
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String Customername= "";
		String msg = "good";
		HttpSession session = request.getSession(true);


		Utilities utility = new Utilities(request, pw);
		if(!utility.isLoggedin())
		{
			//HttpSession session = request.getSession(true);
				session = request.getSession(true);
			session.setAttribute("login_msg", "Please Login to Pay");
			response.sendRedirect("Login");
			return;
		}
		// get the payment details like credit card no address processed from cart servlet	

		String userAddress=request.getParameter("userAddress");
		String creditCardNo=request.getParameter("creditCardNo");
		String state=request.getParameter("state");
		
		if(session.getAttribute("usertype").equals("retailer")){
			Customername =request.getParameter("customername");
			try{
				HashMap<String,User> hm=new HashMap<String,User>();
				hm=MySqlDataStoreUtilities.selectUser();
				if(hm.containsKey(Customername)){
					if(hm.get(Customername).getUsertype().equals("customer")){
						msg ="good";
					}else {msg ="bad";}
						
				}else {msg ="bad";}
				
			}catch(Exception e)
			{
				
			}
		}

		utility.printHtml("Header.html");
		utility.printHtml("Left_Nav.html");
		pw.print("<div class=\"col-md-9\"><div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");

		String message=MySqlDataStoreUtilities.getConnection();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			String orderdate = dateFormat.format(today);
			SimpleDateFormat df = new SimpleDateFormat("HHmmss");
			
			SimpleDateFormat deliveryDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String deliverydate = deliveryDateFormat.format(new Date());
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(deliveryDateFormat.parse(deliverydate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DATE, 14);  // number of days to add
			 deliverydate = deliveryDateFormat.format(c.getTime());
		if(message.equals("Successfull"))
		{
			if (msg.equals("good"))
			{
				int orderId=utility.getOrderPaymentSize()+1;
				//iterate through each order

				for (OrderItem oi : utility.getCustomerOrders())
				{

					
					//set the parameter for each column and execute the prepared statement

					utility.storePayment(orderId,oi.getName(),oi.getPrice(),userAddress,creditCardNo,Customername,oi.getWarranty(),oi.getDiscount(),orderdate,deliverydate,state);
					/*String id,String name, double price, String image, String retailer,int warranty,double discount
					
				double orderPrice,String userAddress,String creditCardNo,String customer, int warrantyamt, double discount,String orderdate,String deliverydate*/
				}

				//remove the order details from cart after processing
					
				OrdersHashMap.orders.remove(utility.username());
					
						pw.print("<h2>Your Order");
					pw.print("&nbsp&nbsp");  
						pw.print("is stored ");
					pw.print("<br>Your Order No is "+(orderId)+"</h2>");

			

			pw.print("<br><br>Your Order Date is "+orderdate);
			
           
			pw.print("<br><br>Estimated delivery date: " + deliverydate);
            
			}else {pw.print("<h2>Customer does not exits</h2>");}		
		}
		else
		{pw.print("<h2>My Sql server is not up and running</h2>");
		
		}
		pw.print("</div></div></div></div>");		
		utility.printHtml("Footer.html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		
		
	}
}
