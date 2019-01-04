import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.AggregationOutput;
import java.util.*;
                	
public class MongoDBDataStoreUtilities
{
static DBCollection myReviews;
public static void getConnection()
{
MongoClient mongo;
mongo = new MongoClient("localhost", 27017);

DB db = mongo.getDB("CustomerReviews");
 myReviews= db.getCollection("myReviews");			      			
}


public static String insertReview(String productname,String username,String producttype,String productmaker,String reviewrating,String reviewdate,String  reviewtext,String retailername,String retailerpin,String retailercity,String retailerstate,String productprice,String productonsale,String age,String userid,String gender,String occupation,String rebate,String state)
{
	try
		{		
			getConnection();
			BasicDBObject doc = new BasicDBObject("title", "myReviews").
				append("username", username).
				append("productname", productname).
				append("producttype", producttype).
				append("productmaker", productmaker).
				append("reviewrating", reviewrating).
				append("reviewDate", reviewdate).
				append("reviewtext", reviewtext).
				append("retailername",retailername).
				append("retailerpin", retailerpin).
				append("retailercity",retailercity).
				append("retailerstate",retailerstate).
				append("productprice",productprice).
				append("productonsale",productonsale).
				append("age",age).
				append("userid",username).
				append("gender",gender).
				append("occupation",occupation).
				append("rebate",rebate).
				append("state",state);
				System.out.println("RP++++++++++++"+retailerpin);
				System.out.println("RR++++++++++++"+reviewrating);
			myReviews.insert(doc);
			return "Successfull";
		}
		catch(Exception e)
		{
		return "UnSuccessfull";
		}	
		
}

public static HashMap<String, ArrayList<Review>> selectReview()
{	
	HashMap<String, ArrayList<Review>> reviews=null;
	
	try
		{

	getConnection();
	DBCursor cursor = myReviews.find();
	reviews=new HashMap<String, ArrayList<Review>>();
	while (cursor.hasNext())
	{
			BasicDBObject obj = (BasicDBObject) cursor.next();				
	
		   if(!reviews.containsKey(obj.getString("productname")))
			{	
				ArrayList<Review> arr = new ArrayList<Review>();
				reviews.put(obj.getString("productname"), arr);
			}
			ArrayList<Review> listReview = reviews.get(obj.getString("productname"));		
			Review review =new Review(obj.getString("productname"),obj.getString("username"),obj.getString("producttype"),obj.getString("productmaker"),
				obj.getString("reviewrating"),obj.getString("reviewDate"),obj.getString("reviewtext"),obj.getString("retailername"),obj.getString("retailerpin"),obj.getString("retailercity"),obj.getString("retailerstate"),obj.getString("productprice"),obj.getString("productonsale"),obj.getString("age"),obj.getString("userid"),obj.getString("gender"),obj.getString("occupation"),obj.getString("rebate"),obj.getString("state"));
			//add to review hashmap
			listReview.add(review);
		
			}
 		return reviews;
		}
		catch(Exception e)
		{
		 reviews=null;
		 return reviews;	
		}	

     
	}
	

  public static  ArrayList <Bestrating> topProducts(){
	  ArrayList <Bestrating> Bestrate = new ArrayList <Bestrating> ();
	  try{
		  System.out.println("top5");
	  getConnection();
	  int retlimit =5;
	  DBObject sort = new BasicDBObject();
	  sort.put("reviewrating",-1);
	  System.out.println("top51");
	  DBCursor cursor = myReviews.find().limit(retlimit).sort(sort);
	  while(cursor.hasNext()) {
		  System.out.println("top52");
		  BasicDBObject obj = (BasicDBObject) cursor.next();
		  		  		   
		  String prodcutnm = obj.get("productname").toString();
		  String rating = obj.get("reviewrating").toString();
	      Bestrating best = new Bestrating(prodcutnm,rating);
		  Bestrate.add(best);
		  System.out.println("top53");
	  }
	
	}catch (Exception e){ System.out.println(e.getMessage());}
   return Bestrate;
  }
  
  	  public static ArrayList <Mostsoldzip> mostsoldZip(){
	  
	  ArrayList <Mostsoldzip> mostsoldzip = new ArrayList <Mostsoldzip> ();
	  
	  try{
		  System.out.println("top5zip");
	  getConnection();
	  System.out.println("top5zip1");
      DBObject groupProducts = new BasicDBObject("_id","$retailerpin");
		System.out.println("RPZIP++++++++++++"+groupProducts);
	  groupProducts.put("count",new BasicDBObject("$sum",1));
	  DBObject group = new BasicDBObject("$group",groupProducts);
	  DBObject limit=new BasicDBObject();
      limit=new BasicDBObject("$limit",5);
	  System.out.println("top5zip2"+limit);
	  DBObject sortFields = new BasicDBObject("count",-1);
	  System.out.println("top5zip3"+sortFields);
	  DBObject sort = new BasicDBObject("$sort",sortFields);
	  System.out.println("top5zip4"+sort);
	  AggregationOutput output = myReviews.aggregate(group,sort,limit);
	  System.out.println("RPZIP2++++++++++++"+groupProducts+output+sort+sortFields+limit);
      for (DBObject res : output.results()) {
        System.out.println(res);
		String zipcode =(res.get("_id")).toString();
        String count = (res.get("count")).toString();	
        Mostsoldzip mostsldzip = new Mostsoldzip(zipcode,count);
		mostsoldzip.add(mostsldzip);
	System.out.println("top5zip5");
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return mostsoldzip;
  }
  
   public static ArrayList <Mostsold> mostsoldProducts(){
	  ArrayList <Mostsold> mostsold = new ArrayList <Mostsold> ();
	  try{
		  System.out.println("top5productsold");
	  
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
        Mostsold mostsld = new Mostsold(prodcutname,count);
		mostsold.add(mostsld);
		System.out.println("top5productsold12");
	
	  }
	  
	 
	  
	}catch (Exception e){ System.out.println(e.getMessage());}
      return mostsold;
  }	

  //Get all the reviews grouped by product and zip code;
public static ArrayList<Review> selectReviewForChart() {

		
        ArrayList<Review> reviewList = new ArrayList<Review>();
        try {

            getConnection();
            Map<String, Object> dbObjIdMap = new HashMap<String, Object>();
            dbObjIdMap.put("retailerpin", "$retailerpin");
            dbObjIdMap.put("productname", "$productname");
            DBObject groupFields = new BasicDBObject("_id", new BasicDBObject(dbObjIdMap));
            groupFields.put("count", new BasicDBObject("$sum", 1));
            DBObject group = new BasicDBObject("$group", groupFields);

            DBObject projectFields = new BasicDBObject("_id", 0);
            projectFields.put("retailerpin", "$_id");
            projectFields.put("productname", "$productname");
            projectFields.put("reviewCount", "$count");
            DBObject project = new BasicDBObject("$project", projectFields);

            DBObject sort = new BasicDBObject();
            sort.put("reviewCount", -1);

            DBObject orderby = new BasicDBObject();            
            orderby = new BasicDBObject("$sort",sort);
            

            AggregationOutput aggregate = myReviews.aggregate(group, project, orderby);

            for (DBObject result : aggregate.results()) {

                BasicDBObject obj = (BasicDBObject) result;
                Object o = com.mongodb.util.JSON.parse(obj.getString("retailerpin"));
                BasicDBObject dbObj = (BasicDBObject) o;
                Review review = new Review(dbObj.getString("productname"),dbObj.getString("username"),dbObj.getString("retailername"), dbObj.getString("retailerpin"),
                        obj.getString("reviewCount"), null);
                reviewList.add(review);
                
            }
            return reviewList;

        }

        catch (

        Exception e) {
            reviewList = null;
            
            return reviewList;
        }

    }
  

	
}	