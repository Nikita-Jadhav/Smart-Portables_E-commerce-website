import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;



@WebServlet("/Accessory")

/* 
	Accessory class contains class variables name,price,image,retailer,condition,discount.

	Accessory class has a constructor with Arguments name,price,image,retailer,condition,discount.
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount.

*/

public class Accessory extends HttpServlet {
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private int warranty;
	private int quantity;
	private double rebate;
	/*private String orderdate;
	private String deliverydate;*/
	
	
	public Accessory(String name, double price, String image, String retailer,String condition,double discount,int warranty,int quantity,double rebate){
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount;
		this.retailer = retailer;
		this.warranty = warranty;
		this.quantity=quantity;
		this.rebate=rebate;
	/*	this.orderdate = orderdate;
		this.deliverydate = deliverydate;*/
	}
	
	
	public Accessory() {
		
	}
	public double getRebate() {
		return rebate;
	}
	public void setRebate(double rebate) {
		this.rebate=rebate;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

/*	public String getOrderdate(){
		return orderdate;
	}

	public void setOrderdate(String orderdate) {
		this.orderdate=orderdate;
	}
	
	public String getDeliverydate(){
		return deliverydate;
	}

	public void setDeliverydate(String deliverydate) {
		this.deliverydate=deliverydate;
	}*/
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	

}
