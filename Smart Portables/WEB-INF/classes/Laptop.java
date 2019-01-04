import java.util.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Laptop")


/* 
	Laptop class contains class variables name,price,image,retailer,condition,discount and Accessories Hashmap.

	Laptop class constructor with Arguments name,price,image,retailer,condition,discount and Accessories .
	  
	Accessory class contains getters and setters for name,price,image,retailer,condition,discount and Accessories .

*/

public class Laptop extends HttpServlet{
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
	private HashMap<String,String> accessories;

	public Laptop(){
		
	}

	public Laptop(String name, double price, String image, String retailer,String condition,double discount,int warranty,int quantity,double rebate){
		this.name=name;
		this.price=price;
		this.image=image;
		this.retailer = retailer;
		this.condition=condition;
		this.discount = discount;
		this.warranty = warranty;
        this.accessories=accessories;
		this.quantity=quantity;
		this.rebate=rebate;
		/*this.orderdate = orderdate;
		this.deliverydate = deliverydate;*/
	}
	
    HashMap<String,String> getAccessories() {
		return this.accessories;
		}
	
	public double getRebate() {
		return this.rebate;
	}
	public void setRebate(double rebate) {
		this.rebate=rebate;
	}
	public int getWarranty() {
		return this.warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/*public String getOrderdate(){
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
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return this.retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public void setAccessories( HashMap<String,String> accessories) {
		this.accessories = accessories;
	}
	
	public String getCondition() {
		return this.condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getDiscount() {
		return this.discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
