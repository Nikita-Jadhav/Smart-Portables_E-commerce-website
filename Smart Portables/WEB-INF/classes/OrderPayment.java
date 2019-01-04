import java.io.IOException;
import java.io.*;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

/* 
	OrderPayment class contains class variables username,ordername,price,image,address,creditcardno.

	OrderPayment  class has a constructor with Arguments username,ordername,price,image,address,creditcardno
	  
	OrderPayment  class contains getters and setters for username,ordername,price,image,address,creditcardno
*/

public class OrderPayment implements Serializable{
	private int orderId;
	private String userName;
	private String orderName;
	private double orderPrice;
	private String userAddress;
	private String creditCardNo;
	private int warrantyamt;
	private double discount;
	private String orderdate;
	private String deliverydate;
	private String state;
	
	public OrderPayment(int orderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo, double discount,int warrantyamt,String orderdate,String deliverydate,String state){
		this.orderId=orderId;
		this.userName=userName;
		this.orderName=orderName;
	 	this.orderPrice=orderPrice;
		this.userAddress=userAddress;
	 	this.creditCardNo=creditCardNo;
		this.warrantyamt=warrantyamt;
		this.discount=discount;
		this.orderdate=orderdate;
		this.deliverydate=deliverydate;
		this.state=state;
		}
public int getWarrantyId() {
		return warrantyamt;
	}
	public void setWarrantyId(int warrantyamt) {
		this.warrantyamt = warrantyamt;
	}
	
	public String getState(){
	return this.state;
	}
	public void setState(String state){
	this.state=state;
	}
	public String getOrderDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date today = new Date();
			String orderdate = dateFormat.format(today);
		return orderdate;
	}
	public void setOrderDate(String orderdate) {
		this.orderdate=orderdate;
	}
	public String getDeliveryDate() {
			SimpleDateFormat df = new SimpleDateFormat("HHmmss");
			SimpleDateFormat deliveryDateFormat = new SimpleDateFormat("MM/dd/yyyy");
            String deliverydate = deliveryDateFormat.format(new Date());
            Calendar c = Calendar.getInstance();
            try 
			{
                c.setTime(deliveryDateFormat.parse(deliverydate));
            } 
			catch (ParseException e) 
			{
                e.printStackTrace();
            }
            c.add(Calendar.DATE, 14);  // number of days to add
            deliverydate = deliveryDateFormat.format(c.getTime());
		return deliverydate;
	}
	public void setDeliveryDate(String deliverydate) {
		this.deliverydate=deliverydate;
	}
	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public double getDiscountId() {
		return discount;
	}

	public void setDiscountId(double discount) {
		this.discount = discount;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
	

}
