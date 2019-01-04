import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewtext,reviewdate,reviewrating

	Review class has a constructor with Arguments username,productname,reviewtext,reviewdate,reviewrating
	  
	Review class contains getters and setters for username,productname,reviewtext,reviewdate,reviewrating
*/

public class Review implements Serializable{
	private String productname;
	private String username;
	private String producttype;
	private String productmaker;
	private String reviewrating;
	private String reviewdate;
	private String reviewtext;
	private String retailerpin;
	String state;
	String retailercity;
	String retailerstate;
	String productprice;
	String productonsale;
	String age;
	String userid;
	String gender;
	String occupation;
	String rebate;
	String retailername;
	
	
	public Review (String productname,String username,String producttype,String productmaker,String reviewrating,String reviewdate,String reviewtext,String retailername,String retailerpin,String retailercity,String retailerstate,String productprice,String productonsale,String age,String userid,String gender,String occupation,String rebate,String state){
		this.productname=productname;
		this.username=username;
		this.producttype=producttype;
		this.productmaker=productmaker;
	 	this.reviewrating=reviewrating;
		this.reviewdate=reviewdate;
	 	this.reviewtext=reviewtext;
		this.retailername=retailername;
		this.retailerpin=retailerpin;
		this.retailercity=retailercity;
		this.retailerstate=retailerstate;
		this.productprice=productprice;
		this.productonsale=productonsale;
		this.age=age;
		this.userid=userid;
		this.gender=gender;
		this.occupation=occupation;
		this.rebate=rebate;
		this.state=state;
	}
//for printing in view review in web page
	public Review(String productname,String username,String retailername, String retailerpin, String reviewrating, String reviewtext) {
       this.productname = productname;
	   this.username=username;
	   this.retailername=retailername;
       this.retailerpin = retailerpin;
       this.reviewrating = reviewrating;
       this.reviewtext = reviewtext;
	   this.state=state;
    }

	public String getProductName() {
		return productname;
	}

	public void setProductName(String productname) {
		this.productname = productname;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state=state;
	}
	public String getRebate() {
		return rebate;
	}

	public void setRebate(String rebate) {
		this.rebate = rebate;
	}
public String getProductonsale() {
		return productonsale;
	}

	public void setProductonsale(String productonsale) {
		this.productonsale = productonsale;
	}
	public String getProductPrice() {
		return productprice;
	}

	public void setProductPrice(String productprice) {
		this.productprice = productprice;
	}
	public String getProductType() {
		return producttype;
	}

	public void setProductType(String producttype) {
		this.producttype = producttype;
	}

	public String getProductMaker() {
		return productmaker;
	}

	public void setProductMaker(String productmaker) {
		this.productmaker = productmaker;
	}

	

	public String getReviewText() {
		return reviewtext;
	}
	public void setReviewText(String reviewtext) {
		this.reviewtext = reviewtext;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
public String getReviewRating() {
		return reviewrating;
	}
	public void setReviewRating(String reviewrating) {
		this.reviewrating = reviewrating;
	}
	public String getReviewDate() {
		return reviewdate;
	}

	public void setReviewDate(String reviewdate) {
		this.reviewdate = reviewdate;
	}
    public String getRetailerName() {
		return retailername;
	}
	public void setRetailerName(String retailername) {
		this.retailername=retailername;
	}
		public String getRetailerPin() {
		return retailerpin;
	}

	public void setRetailerPin(String retailerpin) {
		this.retailerpin = retailerpin;
	}


}
