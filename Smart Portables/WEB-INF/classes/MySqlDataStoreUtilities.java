import java.sql.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;	
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;


import java.sql.ResultSet;
import java.sql.*;

               	
public class MySqlDataStoreUtilities
{
static Connection conn = null;
static String message;
public static String getConnection()
{

	try
	{
		
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smarttechdb?autoReconnect=true&useSSL=false","root","niku123");							
	message="Successfull";
	//useSSL=false&
	return message;
	}
	catch(SQLException e)
	{
		message="unsuccessful";
		     return message;
	}
	catch(Exception e)
	{
		message=e.getMessage();
		return message;
	}
}

public static void Insertproducts()
{
	try{
		
		getConnection();
		String insertProductQurey = "INSERT INTO  ProductDetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productWarranty,productQuantity,productRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		for(Map.Entry<String,Accessory> entry : SaxParserDataStore.accessories.entrySet())
		{   
			String name = "accessories";
	        Accessory acc = entry.getValue();
			//java.sql.Date getOrderdate = new java.sql.Date(0000-00-00);
System.out.println("IDDDDDDDDDDDDDDDD====="+acc.getId());
System.out.println("IDDDDDDDDDDDDDDDD====="+acc.getName());
System.out.println("IDDDDDDDDDDDDDDDD====="+acc.getPrice());
System.out.println("IDDDDDDDDDDDDDDDD====="+name);

			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			System.out.println("IDDDDDDDDDDDDDDDD====="+name);
			pst.setString(1,name);
			System.out.println("IDDDDDDDDDDDDDDDD====="+name);
			pst.setString(2,acc.getId());
			System.out.println("IDDDDDDDDDDDDDDDD====="+name);
			pst.setString(3,acc.getName());
			pst.setDouble(4,acc.getPrice());
			pst.setString(5,acc.getImage());
			pst.setString(6,acc.getRetailer());
			pst.setString(7,acc.getCondition());
			pst.setDouble(8,acc.getDiscount());
			pst.setInt(9,acc.getWarranty());
			pst.setInt(10,acc.getQuantity());
			pst.setDouble(11,acc.getRebate());
			/*pst.setInt(11,acc.getsoldProductcount());
			pst.setInt(12,acc.getavailProductCount());*/
			//pst.setInt(11,acc.getmanuRebate());
			pst.executeUpdate();
			System.out.println("IDDDDDDDDDDDDDDDD====="+acc.getId());
System.out.println("IDDDDDDDDDDDDDDDD====="+acc.getName());
System.out.println("IDDDDDDDDDDDDDDDD====="+acc.getPrice());
System.out.println("IDDDDDDDDDDDDDDDD====="+name);
			
		}
		
		for(Map.Entry<String,WearableTechnology> entry : SaxParserDataStore.wearabletechnologys.entrySet())
		{   
	       
		   WearableTechnology con = entry.getValue();
			String name = "wearabletechnologys";
			System.out.println("2IDDDDDDDDDDDDDDDD====="+con.getId());
System.out.println("IDDDDDDDDDDDDDDDD====="+con.getName());
System.out.println("IDDDDDDDDDDDDDDDD====="+con.getPrice());
System.out.println("IDDDDDDDDDDDDDDDD====="+name);
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,con.getId());
			pst.setString(3,con.getName());
			pst.setDouble(4,con.getPrice());
			pst.setString(5,con.getImage());
			pst.setString(6,con.getRetailer());
			pst.setString(7,con.getCondition());
			pst.setDouble(8,con.getDiscount());
			pst.setInt(9,con.getWarranty());
			pst.setInt(10,con.getQuantity());
			pst.setDouble(11,con.getRebate());
			/*pst.setInt(11,con.getsoldProductcount());
			pst.setInt(12,con.getavailProductCount());*/
			//pst.setInt(11,con.getmanuRebate());
			
			pst.executeUpdate();
			/*pst.setString(10,con.getOrderdate());
			pst.setString(11,con.getDeliverydate());
			*/
			/*
			pst.executeUpdate();
			try{
			HashMap<String,String> acc = con.getAccessories();
			String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			"VALUES (?,?);";
			for(Map.Entry<String,String> accentry : acc.entrySet())
			{
				PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
				pstacc.setString(1,con.getId());
				pstacc.setString(2,accentry.getValue());
				pstacc.executeUpdate();
			}
			}catch(Exception et){
				et.printStackTrace();
			}
			*/
			
		}
		for(Map.Entry<String,Laptop> entry : SaxParserDataStore.laptops.entrySet())
		{   
			String name = "laptops";
	        Laptop laptop = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,laptop.getId());
			pst.setString(3,laptop.getName());
			pst.setDouble(4,laptop.getPrice());
			pst.setString(5,laptop.getImage());
			pst.setString(6,laptop.getRetailer());
			pst.setString(7,laptop.getCondition());
			pst.setDouble(8,laptop.getDiscount());
			pst.setInt(9,laptop.getWarranty());
			pst.setInt(10,laptop.getQuantity());
			pst.setDouble(11,laptop.getRebate());
			/*pst.setInt(11,laptop.getsoldProductcount());
			pst.setInt(12,laptop.getavailProductCount());*/
			//pst.setInt(11,laptop.getmanuRebate());
			
			/*pst.setString(10,laptop.getOrderdate());
			pst.setString(11,laptop.getDeliverydate());*/
			pst.executeUpdate();
			
			try{
			HashMap<String,String> acc = laptop.getAccessories();
			String insertAccessoryQurey = "INSERT INTO  Product_accessories(productName,accessoriesName)" +
			"VALUES (?,?);";
			for(Map.Entry<String,String> accentry : acc.entrySet())
			{

		PreparedStatement pstacc = conn.prepareStatement(insertAccessoryQurey);
				pstacc.setString(1,laptop.getId());
				pstacc.setString(2,accentry.getValue());
				pstacc.executeUpdate();
			}
			}catch(Exception et){
				et.printStackTrace();
			}
			
		}
		for(Map.Entry<String,Phone> entry : SaxParserDataStore.phones.entrySet())
		{   
			String name = "phones";
	        Phone phone = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,phone.getId());
			pst.setString(3,phone.getName());
			pst.setDouble(4,phone.getPrice());
			pst.setString(5,phone.getImage());
			pst.setString(6,phone.getRetailer());
			pst.setString(7,phone.getCondition());
			pst.setDouble(8,phone.getDiscount());
			pst.setInt(9,phone.getWarranty());
			pst.setInt(10,phone.getQuantity());
			pst.setDouble(11,phone.getRebate());
			/*pst.setInt(11,phone.getsoldProductcount());
			pst.setInt(12,phone.getavailProductCount());*/
			//pst.setInt(11,phone.getmanuRebate());
			
			/*pst.setString(10,phone.getOrderdate());
			pst.setString(11,phone.getDeliverydate());*/
			
			pst.executeUpdate();
			
			
		}
		for(Map.Entry<String,VoiceAssistant> entry : SaxParserDataStore.voiceassistants.entrySet())
		{   
			String name = "voiceassistants";
	        VoiceAssistant voiceassistant = entry.getValue();
			
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			pst.setString(1,name);
			pst.setString(2,voiceassistant.getId());
			pst.setString(3,voiceassistant.getName());
			pst.setDouble(4,voiceassistant.getPrice());
			pst.setString(5,voiceassistant.getImage());
			pst.setString(6,voiceassistant.getRetailer());
			pst.setString(7,voiceassistant.getCondition());
			pst.setDouble(8,voiceassistant.getDiscount());
			pst.setInt(9,voiceassistant.getWarranty());
			pst.setInt(10,voiceassistant.getQuantity());
			pst.setDouble(11,voiceassistant.getRebate());
			/*pst.setInt(11,voiceassistant.getsoldProductcount());
			pst.setInt(12,voiceassistant.getavailProductCount());*/
			//pst.setInt(11,voiceassistant.getmanuRebate());
			
			/*pst.setString(10,voiceassistant.getOrderdate());
			pst.setString(11,voiceassistant.getDeliverydate());*/
			
			pst.executeUpdate();
			
			
		}
		
	}catch(Exception e)
	{
  		e.printStackTrace();
	}
} 

public static HashMap<String,WearableTechnology> getWearableTechnologys()
{	
	HashMap<String,WearableTechnology> hm=new HashMap<String,WearableTechnology>();
	try 
	{	getConnection();
		
		String selectWearableTechnology="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectWearableTechnology);
		pst.setString(1,"wearabletechnologys");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	WearableTechnology con = new WearableTechnology(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"),rs.getDouble("productRebate"));
				hm.put(rs.getString("Id"), con);
				con.setId(rs.getString("Id"));
				System.out.println(rs.getString("Id"));
				/*try
				{
				String selectaccessory = "Select * from Product_accessories where productName=?";
				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
				pstacc.setString(1,rs.getString("Id"));
				ResultSet rsacc = pstacc.executeQuery();
				//System.out.print("assccececeec" + rsacc.getString("accessoriesName"));
				HashMap<String,String> acchashmap = new HashMap<String,String>();
				while(rsacc.next())
				{    
					if (rsacc.getString("accessoriesName") != null){
					System.out.print("acc");
					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
					 con.setAccessories(acchashmap);
					}
					 
				}					
				}catch(Exception e)
				{
						e.printStackTrace();
				}*/
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Laptop> getLaptops()
{	
	HashMap<String,Laptop> hm=new HashMap<String,Laptop>();
	try 
	{
		getConnection();
		
		String selectLaptop="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectLaptop);
		pst.setString(1,"laptops");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Laptop laptop = new Laptop(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"),rs.getDouble("productRebate"));
				hm.put(rs.getString("Id"), laptop);
				laptop.setId(rs.getString("Id"));
		//		System.out.println(rs.getString("Id"));
				try
				{
				String selectaccessory = "Select * from Product_accessories where productName=?";
				PreparedStatement pstacc = conn.prepareStatement(selectaccessory);
				pstacc.setString(1,rs.getString("Id"));
				ResultSet rsacc = pstacc.executeQuery();
				//System.out.print("assccececeec" + rsacc.getString("accessoriesName"));
				HashMap<String,String> acchashmap = new HashMap<String,String>();
				while(rsacc.next())
				{    
					if (rsacc.getString("accessoriesName") != null){
					System.out.print("acc");
					 acchashmap.put(rsacc.getString("accessoriesName"),rsacc.getString("accessoriesName"));
					 laptop.setAccessories(acchashmap);
					}
					 
				}					
				}catch(Exception e)
				{
						e.printStackTrace();
				}

		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Phone> getPhones()
{	
	HashMap<String,Phone> hm=new HashMap<String,Phone>();
	System.out.println("HZZ11"+hm.size());
	try 
	{
		getConnection();
		System.out.println("HZZ12"+hm.size());
		String selectPhone="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectPhone);
		pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Phone phone = new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"),rs.getDouble("productRebate"));
				hm.put(rs.getString("Id"), phone);
				phone.setId(rs.getString("Id"));
				System.out.println(rs.getString("Id"));
				System.out.println("HZZFinal"+hm.size());
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,VoiceAssistant> getVoiceAssistants()
{	
	HashMap<String,VoiceAssistant> hm=new HashMap<String,VoiceAssistant>();
	try 
	{
		getConnection();
		
		String selectVoiceAssistant="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectVoiceAssistant);
		pst.setString(1,"voiceassistants");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	VoiceAssistant voiceassistant = new VoiceAssistant(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"),rs.getDouble("productRebate"));
				hm.put(rs.getString("Id"), voiceassistant);
				voiceassistant.setId(rs.getString("Id"));
		//		System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,Accessory> getAccessories()
{	
	HashMap<String,Accessory> hm=new HashMap<String,Accessory>();
	try 
	{
		System.out.println("HZZ"+hm.size());
		getConnection();
		
		String selectAcc="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectAcc);
		pst.setString(1,"accessories");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Accessory acc = new Accessory(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"),rs.getDouble("productRebate"));
				hm.put(rs.getString("Id"), acc);
				acc.setId(rs.getString("Id"));
				System.out.println(rs.getString("Id"));
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static String addproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,int productWarranty,int productQuantity,double productRebate,String prod)
{
	String msg = "Product is added successfully";
	try{
		
		getConnection();
		String addProductQurey = "INSERT INTO  ProductDetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productWarranty,productQuantity,productRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		   
			String name = producttype;
	        			
			PreparedStatement pst = conn.prepareStatement(addProductQurey);
			pst.setString(1,name);
			pst.setString(2,productId);
			pst.setString(3,productName);
			pst.setDouble(4,productPrice);
			pst.setString(5,productImage);
			pst.setString(6,productManufacturer);
			pst.setString(7,productCondition);
			pst.setDouble(8,productDiscount);
			pst.setInt(9,productWarranty);
			pst.setInt(10,productQuantity);
			pst.setDouble(11,productRebate);
			/*pst.setString(10,productOrderdate);
			pst.setString(11,productDeliverydate);*/
			
			pst.executeUpdate();
			try{
				if (!prod.isEmpty())
				{
					String addaprodacc =  "INSERT INTO  Product_accessories(productName,accessoriesName)" +
					"VALUES (?,?);";
					PreparedStatement pst1 = conn.prepareStatement(addaprodacc);
					pst1.setString(1,prod);
					pst1.setString(2,productId);
					pst1.executeUpdate();
					
				}
			}catch(Exception e)
			{
				msg = "Erro while adding the product";
				e.printStackTrace();
		
			}
			
			
		
	}
	catch(Exception e)
	{
		msg = "Erro while adding the product";
		e.printStackTrace();
		
	}
	return msg;
}
public static String updateproducts(String producttype,String productId,String productName,double productPrice,String productImage,String productManufacturer,String productCondition,double productDiscount,int productWarranty,int productQuantity,double productRebate)
{ 
    String msg = "Product is updated successfully";
	try{
		
		getConnection();
		String updateProductQurey = "UPDATE ProductDetails SET productName=?,productPrice=?,productImage=?,productManufacturer=?,productCondition=?,productDiscount=?,productWarranty=?,productQuantity=?,productRebate=? where Id =?;" ;
		
		   
				        			
			PreparedStatement pst = conn.prepareStatement(updateProductQurey);
			
			pst.setString(1,productName);
			pst.setDouble(2,productPrice);
			pst.setString(3,productImage);
			pst.setString(4,productManufacturer);
			pst.setString(5,productCondition);
			pst.setDouble(6,productDiscount);
			pst.setInt(7,productWarranty);
			pst.setInt(8,productQuantity);
			pst.setDouble(9,productRebate);
			/*pst.setString(8,productOrderdate);
			pst.setString(9,productDeliverydate);*/
			pst.setString(10,productId);
			pst.executeUpdate();
			
			
		
	}
	catch(Exception e)
	{
		msg = "Product cannot be updated";
		e.printStackTrace();
		
	}
 return msg;	
}
public static String deleteproducts(String productId)
{   String msg = "Product is deleted successfully";
	try
	{
		
		getConnection();
		String deleteproductsQuery ="Delete from ProductDetails where Id=?";
		PreparedStatement pst = conn.prepareStatement(deleteproductsQuery);
		pst.setString(1,productId);
		
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			msg = "Proudct cannot be deleted";
	}
	return msg;
}

public static void deleteOrder(int OrderId,String orderName)
{
	try
	{
		
		getConnection();
		String deleteOrderQuery ="Delete from customerorders where OrderId=? and orderName=?";
		PreparedStatement pst = conn.prepareStatement(deleteOrderQuery);
		pst.setInt(1,OrderId);
		pst.setString(2,orderName);
		pst.executeUpdate();
	}
	catch(Exception e)
	{
			
	}
}

public static void insertOrder(int OrderId,String userName,String orderName,double orderPrice,String userAddress,String creditCardNo,double discount,int warrantyamt,String orderdate,String deliverydate,String state)
{
	try
	{
	
		getConnection();
		System.out.println("order");
		String insertIntoCustomerOrderQuery = "INSERT INTO customerOrders(OrderId,userName,orderName,orderPrice,userAddress,creditCardNo,discount,warrantyamt,orderdate,deliverydate,state) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?);";	
			
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerOrderQuery);
		//set the parameter for each column and execute the prepared statement
		pst.setInt(1,OrderId);
		pst.setString(2,userName);
		pst.setString(3,orderName);
		pst.setDouble(4,orderPrice);
		pst.setString(5,userAddress);
		pst.setString(6,creditCardNo);
		pst.setDouble(7,discount);
		pst.setInt(8,warrantyamt);
		pst.setString(9,orderdate);
		pst.setString(10,deliverydate);
		pst.setString(11,state);
		
		pst.execute();
	}
	catch(Exception e)
	{
	
	}		
}

public static HashMap<Integer, ArrayList<OrderPayment>> selectOrder()
{	

	HashMap<Integer, ArrayList<OrderPayment>> orderPayments=new HashMap<Integer, ArrayList<OrderPayment>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from customerorders";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<OrderPayment> orderList=new ArrayList<OrderPayment>();
		while(rs.next())
		{
			if(!orderPayments.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("OrderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getDouble("discount"),rs.getInt("warrantyamt"),rs.getString("orderdate"),rs.getString("deliverydate"),rs.getString("state"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}


public static void insertUser(String username,String password,String repassword,String usertype)
{
	try
	{	

		getConnection();
		String insertIntoCustomerRegisterQuery = "INSERT INTO Registration(username,password,repassword,usertype) "
		+ "VALUES (?,?,?,?);";	
				
		PreparedStatement pst = conn.prepareStatement(insertIntoCustomerRegisterQuery);
		pst.setString(1,username);
		pst.setString(2,password);
		pst.setString(3,repassword);
		pst.setString(4,usertype);
		pst.execute();
	}
	catch(Exception e)
	{
	
	}	
}

public static HashMap<String,User> selectUser()
{	
	HashMap<String,User> hm=new HashMap<String,User>();
	try 
	{
		getConnection();
		Statement stmt=conn.createStatement();
		String selectCustomerQuery="select * from  Registration";
		ResultSet rs = stmt.executeQuery(selectCustomerQuery);
		while(rs.next())
		{	User user = new User(rs.getString("username"),rs.getString("password"),rs.getString("usertype"));
				hm.put(rs.getString("username"), user);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}
/*public static HashMap<String,Phone> availablequantity()
	{
		System.out.println("HHH");
		HashMap<String,Phone> hm1 = new HashMap<String,Phone>();
		
		try
		{
			System.out.println("HHH"+hm1.size());
			getConnection();
			String query123 = "select productName,productQuantity,productPrice from ProductDetails";
		//	OrderDetails order=null;	
			PreparedStatement pst=conn.prepareStatement(query123);
			ResultSet rs =pst.executeQuery();
			
			while(rs.next())
			{
				Phone ap=new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"));
				hm1.put(rs.getString("productQuantity"),ap);
				System.out.println("HHH"+hm1.size());
				ap.setQuantity(rs.getInt("productQuantity"));
			/*AvailableProduct availableProduct = new AvailableProduct();	
			availableProduct.setProductname(rs.getString("productname"));
            availableProduct.setQuantity(rs.getInt("quantity"));
            availableProduct.setPrice(rs.getDouble("price"));
            availableproducts.add(availableProduct);*/				
		/*	}
		}
		catch(Exception ex)
		{
			
		}
		return hm1;
	}*/
	public static ResultSet getDiscountedproductnames()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select distinct(productName),productPrice,productDiscount from ProductDetails where productDiscount>0.0 order by productName asc;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet availablequantity()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select productdetails.productName, productdetails.productPrice,productdetails.productQuantity-count((customerorders.orderId)) as remquantity from productdetails left join customerorders on productdetails.productName = customerorders.orderName group by productdetails.productName,customerorders.orderName;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet getRebatedItems()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select distinct (productName),productRebate from  ProductDetails where productRebate>0.0 order by productName asc;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}

public static ResultSet getProductsold(/*String orderName*//*,double orderPrice,int count1,double totsales*/)
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="SELECT orderName,orderPrice,count(orderName) as count1,(orderPrice*count(orderName)) as totsales from customerorders /*where orderName = ? */group by orderName;";			
		
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		//pst.setString(1,orderName);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet getTotdailysale()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select orderDate,SUM(orderPrice) as totsales from customerorders group by orderDate;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet getprobought()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select orderName from customerorders group by userAddress;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet getavgsold()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select AVG(orderPrice) as avgsales from customerorders group by userAddress;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();
/*
		workbook.easy_WriteXLSFile_FromResultSet("C:\\Users\\Shri Ram\\Documents\\ResultSet to Excel.xls", 
		rs, new ExcelAutoFormat(Styles.AUTOFORMAT_EASYXLS1), "ResultSet");
		if (workbook.easy_getError().equals(""))
                System.out.println("File successfully created.");
            else
                System.out.println("Error encountered: " + workbook.easy_getError());
*/
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet gettotalsold()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select SUM(orderPrice) as totsales from customerorders group by userAddress;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet getprosoldbystate()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select count(orderID),state,orderName,orderPrice from customerorders group by state;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet getavgbystate()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select state,AVG(orderPrice) as Avgsales from customerorders group by state;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ArrayList<String> getsumbystate()
{
	ArrayList<String> orders=new ArrayList<String>();
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select state,SUM(orderPrice) as Totsales from customerorders group by state;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();
		
		

		//ArrayList<String> list=new ArrayList<String>();
		while(rs.next())
		{
			//pw.println(" ['" + rs.getString("state") + "', " + rs.getDouble("Totsales")  + "],");
			String val=rs.getString("state")+","+rs.getDouble("Totsales");
			orders.add(val);
			//tring csv=list.toString();
			//String csvWithQuote = list.toString().replace("[", "['").replace("]", "']").replace(", ", "','");
		}
		//pw.println("]);");
		System.out.println("LIST$$$ "+orders);		
	}
	catch(Exception e)
	{
		
	}
	return orders;
}

public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();
			Statement stmt=conn.createStatement();
			String selectCustomerQuery="select * from  productdetails";
			ResultSet rs = stmt.executeQuery(selectCustomerQuery);
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"),rs.getDouble("productRebate"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return hm;			
	}

/*
public static ResultSet getminReview()
{
	ResultSet rs=null;
	HashMap<String, ArrayList<Review>> hm= MongoDBDataStoreUtilities.selectReview();
	String reviewrating = "";
	try{
	for (Review r : hm.get(productname)) 
    {
			reviewrating = r.getReviewRating().toString();
			try
			{
				getConnection();
				String query="select MIN(reviewrating) from Review where  
			}
	catch(Exception e)
	{
			 System.out.println(e.getMessage());
	}
		
		
}*/
/*
public static ResultSet getopfile()
{
	String csvFile = "C:/test3/output.csv";
	ResultSet rs = null;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	
	try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] prod_recm = line.split(cvsSplitBy,2);
				rs.setString(prod_recm[0],prod_recm[1]);
            }
			
		} 
	catch (FileNotFoundException e) 
	{
            e.printStackTrace();
	} 
	catch (IOException e) 
	{
            e.printStackTrace();
	}
	finally 
	{
            if (br != null) 
			{
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
	}
		
	return rs;
}
*/
		
/*public static ResultSet getallproducts()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="select * from productdetails;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		rs = pst.executeQuery();					
	}
	catch(Exception e)
	{
		
	}
	return rs;
}
public static ResultSet storeproducts()
{
	ResultSet rs = null;
	try
	{					

		getConnection();
        //select the table 
		//String selectOrderQuery ="select * from productdetails;";			
		String insertProductQurey = "INSERT INTO  ProductDetails(ProductType,Id,productName,productPrice,productImage,productManufacturer,productCondition,productDiscount,productWarranty,productQuantity,productRebate)" +
		"VALUES (?,?,?,?,?,?,?,?,?,?,?);";
		
		PreparedStatement pst = conn.prepareStatement(insertProductQurey);
		rs = pst.executeQuery();	

	
			for(Map.Entry<String, Product> entry : productdata.entrySet())
			{	

				PreparedStatement pst = conn.prepareStatement(insertIntoProductQuery);
				//set the parameter for each column and execute the prepared statement
				pst.setString(1,entry.getValue().getId());
				pst.setString(2,entry.getValue().getName());
				pst.setString(3,entry.getValue().getImage());
				pst.setString(4,entry.getValue().getRetailer());
				pst.setString(5,entry.getValue().getCondition());
				pst.setDouble(6,entry.getValue().getPrice());
				pst.setString(7,entry.getValue().getType());
				pst.setDouble(8,entry.getValue().getDiscount());
				pst.execute();
			}		
	}
	catch(Exception e)
	{
		
	}
	return rs;
}*/
/*
public static HashMap<String, Product> selectInventory() {

        HashMap<String, Product> hm = new HashMap<String, Product>();

        try {

            getConnection();



            String selectAcc = "select * from Productdetails";

            PreparedStatement pst = conn.prepareStatement(selectAcc);

            ResultSet rs = pst.executeQuery();



            while (rs.next()) {

                Product product = new Product(rs.getString("productName"), rs.getDouble("productPrice"), Integer.parseInt(rs.getString("inventory")));

                hm.put(rs.getString("Id"), product);

                product.setId(rs.getString("Id"));

            }

        } catch (Exception e) {

        }

        return hm;

    }
*/

//select orderDate,SUM(orderPrice) as totsales from customerorders group by orderDate;
/*public static int getCount(String name)
	{
		int count=0;
		
		try
		{
			getConnection();
					
	PreparedStatement pst =conn.prepareStatement("SELECT count(productName) as count1 from customerorders where productName = ? group by productName");
	pst.setString(1, name);						
			
			ResultSet rs =pst.executeQuery();		
			
			while(rs.next())
			{
				count = rs.getInt("count1");			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return count;
		
		
	}	
*/
	/*public static HashMap<String,Phone> getDiscountedproductnames()
	{	
	HashMap<String,Phone> alldis=new HashMap<String,Phone>();
	System.out.println("HZZFinalzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+alldis.size());
	try 
	{
		System.out.println("HZZFinal2"+alldis.size());
		getConnection();
		System.out.println("HZZFinal1"+alldis.size());
		String query23="select productName from  ProductDetails where productDiscount>0.0;";
//		&& ProductType=?";
		PreparedStatement pst = conn.prepareStatement(query23);
		//pst.setString(1,"discountphones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
					Phone phone=new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"));
					alldis.put(rs.getString("productName"),phone);
					/*phone.setName(rs.getString("productName"));
					System.out.println(rs.getString("productName"));
					System.out.println("HZZFinalD"+alldis.size());*/
	/*	}
		System.out.println("HZZFinalD"+alldis.size());
		rs.close();
		pst.close();
		conn.close();
	}
	catch(Exception e)
	{
	}
	return alldis;
	}*//*
public static HashMap<String,Phone> getPhones()
{	
	HashMap<String,Phone> hm=new HashMap<String,Phone>();
	System.out.println("HZZ11"+hm.size());
	try 
	{
		getConnection();
		System.out.println("HZZ12"+hm.size());
		String selectPhone="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(selectPhone);
		pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	Phone phone = new Phone(rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getDouble("productDiscount"),rs.getInt("productWarranty"),rs.getInt("productQuantity"));
				hm.put(rs.getString("Id"), phone);
				phone.setId(rs.getString("Id"));
				System.out.println(rs.getString("Id"));
				System.out.println("HZZFinal"+hm.size());
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}*/
/*
	
	public static HashMap<String,Product> getallproductnames()
{	
	HashMap<String,Product> hm=new HashMap<String,Product>();
	try 
	{
		getConnection();
		
		String query="select * from  ProductDetails where ProductType=?";
		PreparedStatement pst = conn.prepareStatement(query);
		//pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			Product pro = new Product(rs.getString("productName"));
				hm.put(rs.getString("productName"), pro);
		}
	}
	catch(Exception e)
	{
	}
	return hm;			
}

public static HashMap<String,DiscountedProduct> getdiscountedproductnames()
{	
	HashMap<String,DiscountedProduct> hm=new HashMap<String,DiscountedProduct>();
	try 
	{
		getConnection();
		
		String query="select productName from  ProductDetails where productDiscount!=0.0";
		PreparedStatement pst = conn.prepareStatement(query);
		//pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			DiscountedProduct dp = new DiscountedProduct(rs.getString("productName"));
				hm.put(rs.getString("productName"), dp);
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}

public static double getdiscount(String name)
	{
		double productDiscount=0;
		
		try
		{
					getConnection();
			PreparedStatement pst =conn.prepareStatement("SELECT discount from ProductDetails where productName = ?");
			pst.setString(1, name);						
			
			ResultSet rs =pst.executeQuery();		
			
			while(rs.next())
			{
				productDiscount = rs.getDouble("productDiscount");			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productDiscount;
	}
	
	public static double getprice(String name)
	{
		double productPrice=0;
		
		try
		{
			getConnection();
					
			PreparedStatement pst =conn.prepareStatement("SELECT productPrice from ProductDetails where productName = ?");
			pst.setString(1, name);						
			
			ResultSet rs =pst.executeQuery();		
			
			while(rs.next())
			{
				productPrice = rs.getInt("productPrice");			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productPrice;
	}
	public static HashMap<String,RebateProduct> getrebatedproductnames()
{	
	HashMap<String,RebateProduct> hm=new HashMap<String,RebateProduct>();
	try 
	{
		getConnection();
		
		String query="select productName from  ProductDetails where manurebate!=0.0";
		PreparedStatement pst = conn.prepareStatement(query);
		//pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			RebateProduct rp = new RebateProduct(rs.getString("productName"));
				hm.put(rs.getString("productName"), rp);
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}

public static double getrebate(String name)
	{
		double manurebate=0;
		
		try
		{
			getConnection();
					
			PreparedStatement pst =conn.prepareStatement("SELECT manurebate from ProductDetails where productName = ?");
			pst.setString(1, name);						
			
			ResultSet rs =pst.executeQuery();		
			
			while(rs.next())
			{
				manurebate = rs.getDouble("manurebate");			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return manurebate;
	}
public static HashMap<String,CustomerProduct> getproductnamecustomerorders()
{	
	HashMap<String,CustomerProduct> hm=new HashMap<String,CustomerProduct>();
	try 
	{
		getConnection();
		
		String query="select distinct productName from  customerorders";
		PreparedStatement pst = conn.prepareStatement(query);
		//pst.setString(1,"phones");
		ResultSet rs = pst.executeQuery();
	
		while(rs.next())
		{	
			CustomerProduct cp = new CustomerProduct(rs.getString("productName"));
				hm.put(rs.getString("productName"), cp);
		}
	}
	catch(Exception e)
	{
	}
	return hm;
}

public static int getCount(String name)
	{
		int count=0;
		
		try
		{
			getConnection();
					
	PreparedStatement pst =conn.prepareStatement("SELECT count(productName) as count1 from customerorders where productName = ? group by productName");
	pst.setString(1, name);						
			
			ResultSet rs =pst.executeQuery();		
			
			while(rs.next())
			{
				count = rs.getInt("count1");			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return count;
		
		
	}	
	public static TreeMap<String,Double> getdailysalesamount()
	{
		TreeMap<String,Double> dailysalesamount = new TreeMap<String,Double>();
				
		try
		{
			getConnection();
				
			String query =  "select CONCAT(substr(c.orderdate,4,7),substr(c.orderdate,24,5)) as 'Day',sum(p.price) as 'Total' "+
							"from customerorders c, ProductDetails p "+
							"where p.productName=c.productName "+
							"group by CONCAT(substr(c.orderdate,4,7),substr(c.orderdate,24,5))";
						
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs =pst.executeQuery();		
			
			while(rs.next())
			{
				dailysalesamount.put(rs.getString("Day"),rs.getDouble("Total"))	;			
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return dailysalesamount;
	}*/
/*
//fetching products from product table
	ArrayList<Products> getProductList(String category)
	{
		ArrayList<Products> productList = new ArrayList<Products>();
		String query;
		try
		{
			getConnection();
			PreparedStatement pst = conn.prepareStatement(insertProductQurey);
			if(category.equalsIgnoreCase(""))
			{
				query = "SELECT * FROM ProductDetails order by productName ASC;";
				PreparedStatement pst = conn.prepareStatement(query);
				//pst = con.prepareStatement(query);
			}
			else
			{
				query = "SELECT * FROM ProductDetails where producttype = ? order by productName ASC ;";
				PreparedStatement pst = conn.prepareStatement(query);
				//pst = con.prepareStatement(query);
				pst.setString(1,producttype);
			}
			
			rs = pst.executeQuery();
			Products product=null;
			while(rs.next())
			{
				ArrayList<String> accessoryList = new ArrayList<String>();
				product = new Products();
				product.setName(rs.getString("productName"));
				product.setPrice(rs.getString("productPrice"));
				product.setImage(rs.getString("productImage"));
				product.setManufacturer(rs.getString("productManufacturer"));
				product.setCondition(rs.getString("productCondition"));
				product.setDiscount(rs.getString("productDiscount"));
				product.setWarranty(rs.getString("productWarranty"));
				//product.setQuantity(rs.getString("productQuantity"));
				//product.setManufacturerRebate(rs.getString("manufacturerRebate"));
				
				//accessory list conversion.
				String s[] = (rs.getString("productAccessoryList")).split(",");
				for(String s1:s)
					accessoryList.add(s1);
				product.setAccessoryList(accessoryList);
				
				productList.add(product);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productList;
	}
	
	//get discounted products
	ArrayList<Products> getDiscountedProducts()
	{
		ArrayList<Products> productList = new ArrayList<Products>();
		String query;
		try
		{
			query = "SELECT * FROM ProductDetails where productDiscount NOT like '0.0' order by productName ASC ;";
			pst = con.prepareStatement(query);
			//pst.setString(1,searchValue);
			
			rs = pst.executeQuery();
			Products product=null;
			while(rs.next())
			{
				ArrayList<String> accessoryList = new ArrayList<String>();
				product = new Products();
				product.setName(rs.getString("productName"));
				product.setPrice(rs.getString("productPrice"));
				product.setImage(rs.getString("productImage"));
				product.setManufacturer(rs.getString("productManufacturer"));
				product.setCondition(rs.getString("productCondition"));
				product.setDiscount(rs.getString("productDiscount"));
				product.setWarranty(rs.getString("productWarranty"));
				/*product.setQuantity(rs.getString("productQuantity"));
				product.setManufacturerRebate(rs.getString("manufacturerRebate"));
				
				//accessory list conversion.
				String s[] = (rs.getString("productAccessoryList")).split(",");
				for(String s1:s)
					accessoryList.add(s1);
				product.setAccessoryList(accessoryList);
				
				productList.add(product);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return productList;
	}
	public static ArrayList <Discounted> discountedProducts(){
	  ArrayList <Discounted> discounted = new ArrayList <Discounted> ();
	  try{
		  System.out.println("discountedProducts");
	  
      getConnection();
      DBObject groupProducts = new BasicDBObject("_id","$productname"); 
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  
	  DBObject sortFields = new BasicDBObject("count",-1);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
	  System.out.println("oun"+output);
	  System.out.println("top5productsold11");
      for (DBObject res : output.results()) {
	  
      
        System.out.println("res"+res);
		String prodcutname =(res.get("_id")).toString();
        String count = (res.get("count")).toString();	
        Discounted mostsld = new Discounted(prodcutname,count);
		discounted.add(mostsld);
		System.out.println("top5productsold12");
	
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return discounted;
  }	

	public static HashMap<Integer, ArrayList<Discounted>> discountedProducts()
{	

	HashMap<Integer, ArrayList<Discounted>> discounted=new HashMap<Integer, ArrayList<Discounted>>();
		
	try
	{					

		getConnection();
        //select the table 
		String selectOrderQuery ="SELECT * FROM ProductDetails where productDiscount NOT like '0.0' order by productName ASC;";			
		PreparedStatement pst = conn.prepareStatement(selectOrderQuery);
		ResultSet rs = pst.executeQuery();	
		ArrayList<Discounted> disList=new ArrayList<Discounted>();
		while(rs.next())
		{
			if(!discounted.containsKey(rs.getInt("OrderId")))
			{	
				ArrayList<OrderPayment> arr = new ArrayList<OrderPayment>();
				orderPayments.put(rs.getInt("OrderId"), arr);
			}
			ArrayList<OrderPayment> listOrderPayment = orderPayments.get(rs.getInt("OrderId"));		
			System.out.println("data is"+rs.getInt("OrderId")+orderPayments.get(rs.getInt("OrderId")));

			//add to orderpayment hashmap
			OrderPayment order= new OrderPayment(rs.getInt("OrderId"),rs.getString("userName"),rs.getString("orderName"),rs.getDouble("orderPrice"),rs.getString("userAddress"),rs.getString("creditCardNo"),rs.getDouble("discount"),rs.getInt("warrantyamt"),rs.getString("orderdate"),rs.getString("deliverydate"));
			listOrderPayment.add(order);
					
		}
				
					
	}
	catch(Exception e)
	{
		
	}
	return orderPayments;
}*/
}	