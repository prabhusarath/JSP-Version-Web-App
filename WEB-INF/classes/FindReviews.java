import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import com.mongodb.AggregationOutput;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;


public class FindReviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
					
		DB db = mongo.getDB("CustomerReviews");
		
		// If the collection does not exists, MongoDB will create it for you
		DBCollection myReviews = db.getCollection("myReviews");
		
		BasicDBObject query = new BasicDBObject();
		BasicDBObject query1 = new BasicDBObject();

				
		try {
			
			// Get the form data
			String productModelName = request.getParameter("productModelName"); 
			String productCategory = request.getParameter("productCategory");
			int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			String retailerName = request.getParameter("retailerName");
			String RetailerZip = request.getParameter("RetailerZip");
			String RetailerCity = request.getParameter("RetailerCity");
			String RetailerState = request.getParameter("RetailerState");
			String ProductOnSale = request.getParameter("ProductOnSale");
			String ManufacturerName = request.getParameter("ManufacturerName");
			String ManufacturerRebate = request.getParameter("ManufacturerRebate");
			String userID = request.getParameter("userID");
			int userAge = Integer.parseInt(request.getParameter("userAge"));
			String userGender = request.getParameter("userGender");  
			String userOccupation = request.getParameter("userOccupation");
			int reviewRating = Integer.parseInt(request.getParameter("reviewRating"));
			String reviewDate = request.getParameter("reviewDate");
			String reviewText = request.getParameter("reviewText");
			String Radio_Max = request.getParameter("Max");
			String Radio_Min = request.getParameter("Min");
			String Radio_Top5 = request.getParameter("Likes");

			boolean Maxonly=false;
			boolean Minonly=false;
			boolean Top5=false;  


			if(Radio_Max != null)
			{

			 Maxonly = true;

			}

			if(Radio_Min != null)
			{

			 Minonly = true;

			}

			if(Radio_Top5 != null)
			{

			 Top5 = true;

			}


			boolean countOnly = false;


/*
			SimpleDateFormat sdfmt2 = new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date dDate = sdfmt2.parse("reviewDate");
			String reviewDate = sdfmt2.format( dDate );


			SimpleDateFormat sdfmt1 = new SimpleDateFormat("dd/MM/yy");
			
			java.util.Date dDate = sdfmt1.parse( strInput );
			String strOutput = sdfmt2.format( dDate );*/


			String compareRating = request.getParameter("compareRating");
			String comparePrice = request.getParameter("comparePrice");
			String compareAge = request.getParameter("compareAge");
			String returnValueDropdown = request.getParameter("returnValue");
			String returnValueGroupDropdown = request.getParameter("returnValueGroup");

			String groupByDropdown = request.getParameter("groupByDropdown");
			String countOnlyDropdown = request.getParameter("CountByDropdown");

			
			//Boolean flags to check the filter settings
			boolean noFilter = false;
			boolean filterByproductModelName = false;
			boolean filterByproductCategory = false;
			boolean filterByproductPrice = false;
			boolean filterByretailerName = false;
			boolean filterByRetailerZip = false;
			boolean filterByRetailerCity = false;
			boolean filterByRetailerState = false;
			boolean filterByProductOnSale = false;
			boolean filterByManufacturerName = false;
			boolean filterByManufacturerRebate = false;
			boolean filterByuserID = false;
			boolean filterByuserAge = false;
			boolean filterByuserGender = false;
			boolean filterByuserOccupation = false;
			boolean filterByreviewRating = false;
			boolean filterByreviewDate = false;
			boolean filterByreviewText = false;


			boolean groupByFilter = false;

			boolean groupBy = false;
			boolean CountBy = false;

			boolean groupByProduct = false;
			boolean groupByproductCategory = false;
			boolean groupByproductPrice = false;
			boolean groupByretailerName = false;
			boolean groupByRetailerZip = false;
			boolean groupByCity = false;
			boolean groupByRetailerState = false;
			boolean groupByProductOnSale = false;
			boolean groupByManufacturerName = false;
			boolean groupByManufacturerRebate = false;
			boolean groupByuserID = false;
			boolean groupByuserAge = false;
			boolean groupByuserGender = false;
			boolean groupByuserOccupation = false;
			boolean groupByreviewRating = false;
			boolean groupByreviewDate = false;
			boolean mostLiked = false;
			
			
			boolean countByTop = true;					
			boolean countByHighPrice = true;					
			boolean countByMost = true;
							
						
			//Get the filters selected
			//Filter - Simple Search
			String[] filters = request.getParameterValues("queryCheckBox");
			//Filters - Group By
			String[] extraSettings = request.getParameterValues("extraSettings");
			
			DBCursor dbCursor = null;
			AggregationOutput aggregateData = null;


			
			
			//Check for extra settings(Grouping Settings)
			if(extraSettings != null){				
				//User has selected extra settings
				
				
				
				for(int x = 0; x <extraSettings.length; x++){
					switch (extraSettings[x]){						
						case "COUNT_ONLY":
							//Not implemented functionality to return count only

						CountBy = true;


							if(countOnlyDropdown.equals("COUNT_TOP_5")){
								countByTop = true;
							}else if(countOnlyDropdown.equals("COUNT_HIGH_PRICE")){
								countByHighPrice = true;
							} else if(countOnlyDropdown.equals("COUNT_MOST_LIKED")){
								countByMost = true;
							}
							break;


						case "GROUP_BY":	

						groupBy = true;
							//Can add more grouping conditions here
							if(groupByDropdown.equals("GROUP_BY_CITY")){
								groupByCity = true;
							}else if(groupByDropdown.equals("GROUP_BY_PRODUCT")){
								groupByProduct = true;
							} else if(groupByDropdown.equals("GROUP_BY_PRODUCT_CATEGORY")){
								groupByproductCategory = true;
							}else if(groupByDropdown.equals("GROUP_BY_PRODUCT_PRICE")){
								groupByproductPrice = true;
							}else if(groupByDropdown.equals("GROUP_BY_RETAILER_NAME")){
								groupByretailerName = true;
							}else if(groupByDropdown.equals("GROUP_BY_RETAILER_ZIP")){
								groupByRetailerZip = true;
							}else if(groupByDropdown.equals("GROUP_BY_RETAILER_STATE")){
								groupByRetailerState = true;
							}else if(groupByDropdown.equals("GROUP_BY_PRODUCT_ON_SALE")){
								groupByProductOnSale = true;
							}else if(groupByDropdown.equals("GROUP_BY_MANUFACTURER_NAME")){
								groupByManufacturerName = true;
							}else if(groupByDropdown.equals("GROUP_BY_MANUFACTURER_REBATE")){
								groupByManufacturerRebate = true;
							}else if(groupByDropdown.equals("GROUP_BY_USER_ID")){
								groupByuserID = true;
							}else if(groupByDropdown.equals("GROUP_BY_USER_AGE")){
								groupByuserAge = true;
							}else if(groupByDropdown.equals("GROUP_BY_USER_GENDER")){
								groupByuserGender = true;
							}else if(groupByDropdown.equals("GROUP_BY_USER_OCCUPATION")){
								groupByuserOccupation = true;
							}else if(groupByDropdown.equals("GROUP_BY_REVIEW_RATING")){
								groupByreviewRating = true;
							}else if(groupByDropdown.equals("GROUP_BY_REVIEW_DATE")){
								groupByreviewDate = true;
							}
							else if(groupByDropdown.equals("TOP_5_BY_CITY")){
								mostLiked = true;
							}
							break;
					}		
				}				
			}			
			
			//Check the main filters only if the 'groupBy' option is not selected


			if(filters != null && groupBy != true){
				groupByFilter = true;
				for (int i = 0; i < filters.length; i++) {
					//Check what all filters are ON
					//Build the query accordingly
					switch (filters[i])

					{

						case "productModelName":							
							filterByproductModelName = true;
							if (productModelName.equals("XBoxOriginal")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("XBox360")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("XBoxOne")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation3-LimitedEdition")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation3-BlackEdition")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation3-YellowEdition")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation4-BATMANEdition")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("AssasinsCreed4-PS3")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("GodOfWar3-PS4")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("Mario8Racing-WII")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("AllStarsBattleRoyal-PSVITA")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("WII-1X")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("WII-2X")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("WII-U")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("PS4-RaceController")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("X_BOXHandheld")){
								query.put("productModelName", productModelName);
							}else if (productModelName.equals("WIIController-SpecialEdition")){
								query.put("productModelName", productModelName);
							}else if(!productModelName.equals("ALL_PRODUCTS")){
								query.put("productModelName", productModelName);
							}
							break;


							case "productCategory":	
							filterByproductCategory = true;
							if (productCategory.equals("Console")){
								query.put("productCategory", productCategory);
							}else if (productCategory.equals("Games")){
								query.put("productCategory", productCategory);
							} else if (productCategory.equals("Accessories")){
								query.put("productCategory", productCategory);
							} else if (!productCategory.equals("All")){
								query.put("productCategory", productCategory);
							}
							break;

												
						case "productPrice":
							filterByproductPrice = true;
							if (comparePrice.equals("EQUALS_TO")) {
								query.put("productPrice", productPrice);
							}else if(comparePrice.equals("GREATER_THAN")){
								query.put("productPrice", new BasicDBObject("$gt", productPrice));
							}else if(comparePrice.equals("LESS_THAN")){
								query.put("productPrice", new BasicDBObject("$lt", productPrice));
							}
							break;



							case "retailerName":
							filterByretailerName = true;
							if (retailerName.equals("GameSpeed")) {
								query.put("retailerName", retailerName);
							}else if(retailerName.equals("EPLAY")){
								query.put("retailerName", retailerName);
							}else if(!retailerName.equals("All")){
								query.put("retailerName", retailerName);
							}
							break;


												
						case "RetailerZip":
							filterByRetailerZip = true;
							if (RetailerZip.equals("60616")) {
								query.put("RetailerZip", RetailerZip);
							}else if(RetailerZip.equals("77001")){
								query.put("RetailerZip", RetailerZip);
							}else if (RetailerZip.equals("10001")) {
								query.put("RetailerZip", RetailerZip);
							}else if(RetailerZip.equals("90001")){
								query.put("RetailerZip", RetailerZip);
							}else if(!RetailerZip.equals("All")){
								query.put("RetailerZip", RetailerZip);
							}
							break;


												
						case "RetailerCity": 
							filterByRetailerCity = true;
							if(RetailerCity.equals("Chicago")){
								query.put("RetailerCity", RetailerCity);
							}else if(RetailerCity.equals("Houston") ){
								query.put("RetailerCity", RetailerCity);
							}else if(RetailerCity.equals("New York City")){
								query.put("RetailerCity", RetailerCity);
							}else if(RetailerCity.equals("Los Angeles")){
								query.put("RetailerCity", RetailerCity);
							}else if(!RetailerCity.equals("All")){
								query.put("RetailerCity", RetailerCity);
							}	

							break;

							case "RetailerState": 
							filterByRetailerState = true;
							if(RetailerState.equals("New York")){
								query.put("RetailerState", RetailerState);
							}else if(RetailerState.equals("California") ){
									query.put("RetailerState", RetailerState);
							}else if(RetailerState.equals("Illinois")){
									query.put("RetailerState", RetailerState);
							}else if(RetailerState.equals("Texas")){
									query.put("RetailerState", RetailerState);
							}	
							break;

							case "ProductOnSale": 
							filterByProductOnSale = true;
							if(ProductOnSale.equals("Yes")){
								query.put("ProductOnSale", ProductOnSale);
							}else if(ProductOnSale.equals("No") ){
									query.put("ProductOnSale", ProductOnSale);
							}
							break;

							case "ManufacturerName": 
							filterByManufacturerName = true;
							if(ManufacturerName.equals("MICROSOFT")){
								query.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("SONY") ){
									query.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("Nintendo")){
									query.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("ElectronicArts")){
									query.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("Activision")){
									query.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("TakeTwoInteractive")){
									query.put("ManufacturerName", ManufacturerName);
							}else if(!ManufacturerName.equals("All")){
								query.put("ManufacturerName", ManufacturerName);
							}	
							break;

							case "ManufacturerRebate": 
							filterByManufacturerRebate = true;
							if(ManufacturerRebate.equals("Yes")){
								query.put("ManufacturerRebate", ManufacturerRebate);
							}else if(ManufacturerRebate.equals("No") ){
									query.put("ManufacturerRebate", ManufacturerRebate);
							}
							break;



							case "userID":
							filterByuserID = true;
							query.put("userID", userID);
							break;


							case "userAge":
							filterByuserAge = true;
							if (compareAge.equals("EQUALS_TO")) {
								query.put("userAge", userAge);
							}else if(compareAge.equals("GREATER_THAN")){
								query.put("userAge", new BasicDBObject("$gt", userAge));
							}else if(compareAge.equals("LESS_THAN")){
								query.put("userAge", new BasicDBObject("$lt", userAge));
							}
							break;

							case "userGender": 
							filterByuserGender = true;
							if(userGender.equals("Male")){
								query.put("userGender", userGender);
							}else if(userGender.equals("Female") ){
									query.put("userGender", userGender);
							}
							break;


							case "userOccupation":
							filterByuserOccupation = true;
							query.put("userOccupation", userOccupation);
							break;

												
							case "reviewRating":	
							filterByreviewRating = true;
							if (compareRating.equals("EQUALS_TO")) {
								query.put("reviewRating", reviewRating);
							}else if (compareRating.equals("GREATER_THAN")) {
								query.put("reviewRating", new BasicDBObject("$gt", reviewRating));
							}
							else if(compareRating.equals("LESS_THAN")){
								query.put("reviewRating", new BasicDBObject("$lt", reviewRating));
								}

							break;


							case "reviewDate":
							filterByreviewDate = true;
							query.put("reviewDate", reviewDate);
							break;


							case "reviewText":
							filterByreviewText = true;
							query.put("reviewText",reviewText);
							//query.put("reviewText",reviewText.matches("(.*)"+reviewText+"(.*)"));
							break;

													
						default:
							//Show all the reviews if nothing is selected
							noFilter = true;
							break;						
					}				
				}
			}

			else

			{
				//Show all the reviews if nothing is selected
				noFilter = true;
			}

						
			//Construct the top of the page
			constructPageTop(output);
						
			//Run the query 

			DBObject match = null;
			DBObject matchFields= new BasicDBObject();
			
			if(filters != null && groupBy == true){
				groupByFilter = true;
				for (int i = 0; i < filters.length; i++) {
					//Check what all filters are ON
					//Build the query accordingly
					switch (filters[i])

					{

						case "productModelName":							
							filterByproductModelName = true;
							if (productModelName.equals("XBoxOriginal")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("XBox360")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("XBoxOne")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation3-LimitedEdition")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation3-BlackEdition")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation3-YellowEdition")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("PlayStation4-BATMANEdition")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("AssasinsCreed4-PS3")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("GodOfWar3-PS4")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("Mario8Racing-WII")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("AllStarsBattleRoyal-PSVITA")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("WII-1X")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("WII-2X")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("WII-U")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("PS4-RaceController")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("X_BOXHandheld")){
								matchFields.put("productModelName", productModelName);
							}else if (productModelName.equals("WIIController-SpecialEdition")){
								matchFields.put("productModelName", productModelName);
							}else if(!productModelName.equals("ALL_PRODUCTS")){
								matchFields.put("productModelName", productModelName);
							}
							break;


							case "productCategory":	
							filterByproductCategory = true;
							if (productCategory.equals("Console")){
								matchFields.put("productCategory", productCategory);
							}else if (productCategory.equals("Games")){
								matchFields.put("productCategory", productCategory);
							} else if (productCategory.equals("Accessories")){
								matchFields.put("productCategory", productCategory);
							}
							break;

												
						case "productPrice":
							filterByproductPrice = true;
							if (comparePrice.equals("EQUALS_TO")) {
								matchFields.put("productPrice", productPrice);
							}else if(comparePrice.equals("GREATER_THAN")){
								matchFields.put("productPrice", new BasicDBObject("$gt", productPrice));
							}else if(comparePrice.equals("LESS_THAN")){
								matchFields.put("productPrice", new BasicDBObject("$lt", productPrice));
							}
							break;



							case "retailerName":
							filterByretailerName = true;
							if (retailerName.equals("GameSpeed")) {
								matchFields.put("retailerName", retailerName);
							}else if(retailerName.equals("EPLAY")){
								matchFields.put("retailerName", retailerName);
							}
							break;


												
						case "RetailerZip":
							filterByRetailerZip = true;
							if (RetailerZip.equals("60616")) {
								matchFields.put("RetailerZip", RetailerZip);
							}else if(RetailerZip.equals("77001")){
								matchFields.put("RetailerZip", RetailerZip);
							}else if (RetailerZip.equals("10001")) {
								matchFields.put("RetailerZip", RetailerZip);
							}else if(RetailerZip.equals("90001")){
								matchFields.put("RetailerZip", RetailerZip);
							}
							break;


												
						case "RetailerCity": 
							filterByRetailerCity = true;
							if(RetailerCity.equals("Chicago")){
								matchFields.put("RetailerCity", RetailerCity);
							}else if(RetailerCity.equals("Houston") ){
								matchFields.put("RetailerCity", RetailerCity);
							}else if(RetailerCity.equals("New York City")){
								matchFields.put("RetailerCity", RetailerCity);
							}else if(RetailerCity.equals("Los Angeles")){
								matchFields.put("RetailerCity", RetailerCity);
							}	

							break;

							case "RetailerState": 
							filterByRetailerState = true;
							if(RetailerState.equals("New York")){
								matchFields.put("RetailerState", RetailerState);
							}else if(RetailerState.equals("California") ){
									matchFields.put("RetailerState", RetailerState);
							}else if(RetailerState.equals("Illinois")){
									matchFields.put("RetailerState", RetailerState);
							}else if(RetailerState.equals("Texas")){
									matchFields.put("RetailerState", RetailerState);
							}	
							break;

							case "ProductOnSale": 
							filterByProductOnSale = true;
							if(ProductOnSale.equals("Yes")){
								matchFields.put("ProductOnSale", ProductOnSale);
							}else if(ProductOnSale.equals("No") ){
									matchFields.put("ProductOnSale", ProductOnSale);
							}
							break;

							case "ManufacturerName": 
							filterByManufacturerName = true;
							if(ManufacturerName.equals("MICROSOFT")){
								matchFields.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("SONY") ){
									matchFields.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("Nintendo")){
									matchFields.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("ElectronicArts")){
									matchFields.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("Activision")){
									matchFields.put("ManufacturerName", ManufacturerName);
							}else if(ManufacturerName.equals("TakeTwoInteractive")){
									matchFields.put("ManufacturerName", ManufacturerName);
							}	
							break;

							case "ManufacturerRebate": 
							filterByManufacturerRebate = true;
							if(ManufacturerRebate.equals("Yes")){
								matchFields.put("ManufacturerRebate", ManufacturerRebate);
							}else if(ManufacturerRebate.equals("No") ){
									matchFields.put("ManufacturerRebate", ManufacturerRebate);
							}
							break;



							case "userID":
							filterByuserID = true;
							matchFields.put("userID", userID);
							break;


							case "userAge":
							filterByuserAge = true;
							if (compareAge.equals("EQUALS_TO")) {
								matchFields.put("userAge", userAge);
							}else if(compareAge.equals("GREATER_THAN")){
								matchFields.put("userAge", new BasicDBObject("$gt", userAge));
							}else if(compareAge.equals("LESS_THAN")){
								matchFields.put("userAge", new BasicDBObject("$lt", userAge));
							}
							break;

							case "userGender": 
							filterByuserGender = true;
							if(userGender.equals("Male")){
								matchFields.put("userGender", userGender);
							}else if(userGender.equals("Female") ){
									matchFields.put("userGender", userGender);
							}
							break;


							case "userOccupation":
							filterByuserOccupation = true;
							matchFields.put("userOccupation", userOccupation);
							break;

												
							case "reviewRating":	
							filterByreviewRating = true;
							if (compareRating.equals("EQUALS_TO")) {
								matchFields.put("reviewRating", reviewRating);
							}else if (compareRating.equals("GREATER_THAN")) {
								matchFields.put("reviewRating", new BasicDBObject("$gt", reviewRating));
							}
							else if(compareRating.equals("LESS_THAN")){
								matchFields.put("reviewRating", new BasicDBObject("$lt", reviewRating));
								}

							break;


							case "reviewDate":
							filterByreviewDate = true;
							matchFields.put("reviewDate", reviewDate);
							break;


							case "reviewText":
							filterByreviewText = true;
							matchFields.put("reviewText",reviewText);
							//query.put("reviewText",reviewText.matches("(.*)"+reviewText+"(.*)"));
							break;
									
						default:
							//Show all the reviews if nothing is selected
							noFilter = true;
							break;						
					}				
				}
			}

			else

			{
				//Show all the reviews if nothing is selected
				noFilter = true;
			}




			if(groupBy == true){		
				//Run the query using aggregate function


				DBObject groupFields = null;
				DBObject group = null;
				DBObject projectFields = null;
				DBObject project = null;
				AggregationOutput aggregate = null;


				
				if(groupByCity){
					

					if(groupByFilter == true)

					{

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$RetailerCity");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice")); 
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
	
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("City", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");

					project = new BasicDBObject("$project", projectFields);

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);

					}

					else 
					{

						groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$RetailerCity");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice")); 
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
	
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("City", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");

					project = new BasicDBObject("$project", projectFields);

					aggregate = myReviews.aggregate(group, project);
					}

					//Construct the page content
					constructGroupByCityContent(aggregate, output, Maxonly , Minonly,Top5);
					
				}else if(groupByproductCategory){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$productCategory");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice")); 
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("ProductCategory", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByProductCategoryContent(aggregate, output, Maxonly,Minonly);
					
				}else if(groupByProduct){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$productModelName");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("Product", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);

					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByProductContent(aggregate, output, Maxonly,Minonly);
					
				}else if(groupByproductPrice){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$productPrice");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("ProductPrice", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByProductPriceContent(aggregate, output, Maxonly,Minonly);
					
				}
				else if(groupByretailerName){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$retailerName");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("retailerName", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
				
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByretailerNameContent(aggregate, output, Maxonly,Minonly);
					
				}		
				else if(groupByRetailerZip){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$RetailerZip");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("RetailerZip", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByRetailerZipContent(aggregate, output, Maxonly,Minonly);
					
				}	
				else if(groupByRetailerState){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$RetailerState");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("RetailerState", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByRetailerStateContent(aggregate, output, Maxonly,Minonly);
					
				}
				else if(groupByProductOnSale){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$ProductOnSale");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("ProductOnSale", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByProductOnSaleContent(aggregate, output, Maxonly,Minonly);
					
				}
				else if(groupByManufacturerName){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$ManufacturerName");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("ManufacturerName", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByManufacturerNameContent(aggregate, output, Maxonly,Minonly);
					
				}		
				else if(groupByManufacturerRebate){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$ManufacturerRebate");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("ManufacturerRebate", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByManufacturerRebateContent(aggregate, output, Maxonly,Minonly);
					
				}	
				else if(groupByuserID){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$userID");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("userID", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByuserIDContent(aggregate, output, Maxonly,Minonly);
					
				} 
				else if(groupByuserAge){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$userAge");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("userAge", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByuserAgeContent(aggregate, output, Maxonly,Minonly);
					
				} 
				else if(groupByuserGender){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$userGender");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("userGender", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByuserGenderContent(aggregate, output, Maxonly,Minonly);
					
				} 
				else if(groupByuserOccupation){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$userOccupation");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("userOccupation", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByuserOccupationContent(aggregate, output, Maxonly,Minonly);
					
				} 
				else if(groupByreviewRating){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$reviewRating");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("reviewRating", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByreviewRatingContent(aggregate, output, Maxonly,Minonly);
					
				} 
				else if(groupByreviewDate){	

					groupFields = new BasicDBObject("_id", 0);
					groupFields.put("_id", "$reviewDate");
					groupFields.put("count", new BasicDBObject("$sum", 1));
					groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));
					groupFields.put("review", new BasicDBObject("$push", "$reviewText"));
					groupFields.put("rating", new BasicDBObject("$push", "$reviewRating"));
					groupFields.put("price", new BasicDBObject("$push", "$productPrice"));
					groupFields.put("Max", new BasicDBObject("$max", "$productPrice"));
					groupFields.put("Min", new BasicDBObject("$min", "$productPrice"));
					
					group = new BasicDBObject("$group", groupFields);

					projectFields = new BasicDBObject("_id", 0);
					projectFields.put("reviewDate", "$_id");
					projectFields.put("Review Count", "$count");
					projectFields.put("Product", "$productModelName");
					projectFields.put("User", "$userName");
					projectFields.put("Reviews", "$review");
					projectFields.put("Rating", "$rating");
					projectFields.put("Price","$price");
					projectFields.put("MaxPrice","$Max");
					projectFields.put("MinPrice","$Min");
										
					project = new BasicDBObject("$project", projectFields);
					
					if(groupByFilter == true)

					{

						match= new BasicDBObject("$match",matchFields);
						aggregate= myReviews.aggregate(match,group, project);
					}

					else
					{
					aggregate = myReviews.aggregate(group, project);
					}				
							
					//Construct the page content
					constructGroupByreviewDateContent(aggregate, output, Maxonly,Minonly);
					
				} 
		
			
			 }


			else{
				//Check the return value selected
				int returnLimit = 0;
				

				//Create sort variable
				DBObject sort = new BasicDBObject();
												
				if (returnValueDropdown.equals("TOP_5")){
					//Top 5 - Sorted by review rating
					returnLimit = 5;
					sort.put("reviewRating",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("TOP_10")){
					//Top 10 - Sorted by review rating
					returnLimit = 10;
					sort.put("reviewRating",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("LATEST_5")){
					//Latest 5 - Sort by date
					returnLimit = 5;
					sort.put("reviewDate",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else if (returnValueDropdown.equals("LATEST_10")){
					//Latest 10 - Sort by date
					returnLimit = 10;
					sort.put("reviewDate",-1);
					dbCursor = myReviews.find(query).limit(returnLimit).sort(sort);
				}else{
					//Run the simple search query(default result)
					dbCursor = myReviews.find(query);
				}
				
				//Construct the page content
				constructDefaultContent(dbCursor, output, countOnly);
			}			
			
			//Construct the bottom of the page
			constructPageBottom(output);
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}

	
	public void constructPageTop(PrintWriter output){
		String pageHeading = "Query Result";
		String myPageTop = "<!DOCTYPE html>" + "<html lang=\"en\">"
					+ "<head>	<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />"
					+ "<title>Game Speed</title>"
					+ "<link rel=\"stylesheet\" href=\"css/styles.css\" type=\"text/css\" />"
					+ "</head>"
					+ "<body>"
					+ "<div id=\"container\">"
					+ "<header>"
					+ "<h1><a href=\"/\">DATA ANALYTICS<span></span></a></h1><h2>EPLAY</h2>"
					+ "</header>"
					+ "<nav>"
					+ "<ul>"
					+ "<li class=\"\"><a href=\"index.html\">Home</a></li>"
					+ "<li class = \"\"><a href=\"SubmitReview.html\">Write Review</a></li>"
					+ "<li class = \"start selected\"><a href=\"DataAnalytics.html\">Data Analytics</a></li>"
					+ "</ul>"
					+ "</nav>"
					+ "<div id=\"body\">"
					+ "<section id=\"review-content\">"
					+ "<article>"
					+ "<h2 style=\"color:#DE2D3A;font-weight:700;\">" +pageHeading + "</h2>";
		
		output.println(myPageTop);		
	}
	
	public void constructPageBottom(PrintWriter output){
		String myPageBottom =  "</article>"
					+ "</section>"
                    + "<div class=\"clear\"></div>"
					+ "</div>"
					+ "<footer>"
					+ "<div class=\"footer-bottom\">"
					+ "<p> Copyright 2010-2015 EPLAY International LLC. All Rights Reserved.</p>"
					+ "</div>"
					+ "</footer>"
					+ "</div>"
					+ "</body>"
					+ "</html>";

		output.println(myPageBottom);
	}
	
	public void constructDefaultContent(DBCursor dbCursor, PrintWriter output, boolean countOnly){
		int count = 0;
		String tableData = " ";
		String pageContent = " ";

		while (dbCursor.hasNext()) {		
			BasicDBObject bobj = (BasicDBObject) dbCursor.next();
			tableData =  "<tr><td>Product Name: <b>     " + bobj.getString("productModelName") + " </b></td></tr>"  
							+ "<tr><td>Product Category:       " + bobj.getString("productCategory") + "</br>"     
						+ "Product Price:       " + bobj.getInt("productPrice") + "</br>"
						+ "Retailer Name:            " + bobj.getString("retailerName") + "</br>"
						+ "Retailer Zipcode:    " + bobj.getString("RetailerZip") + "</br>"
						+ "Retailer City:       " + bobj.getString("RetailerCity") + "</br>"
						+ "Retailer State:      " + bobj.getString("RetailerState") + "</br>"
						+ "On Sale:                " + bobj.getString("ProductOnSale") + "</br>"
						+ "User ID:             " + bobj.getString("userID") + "</br>"
						+ "User Age:            " + bobj.getString("userAge") + "</br>"
						+ "User Gender:         " + bobj.getString("userGender") + "</br>"
						+ "User Occupation:     " + bobj.getString("userOccupation") + "</br>"
						+ "Manufacturer:        " + bobj.getString("ManufacturerName") + "</br>"
						+ "Manufacturer Rebate: " + bobj.getString("ManufacturerRebate") + "</br>"
						+ "Rating:              " + bobj.getString("reviewRating") + "</br>"
						+ "Date:                " + bobj.getString("reviewDate") + "</br>"
						+ "Review Text:         " + bobj.getString("reviewText") + "</td></tr>";


				
			count++;
				
				output.println("<h3>"+count+"</h3>");
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
		}

		//No data found
		if(count == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

		
	
	public void constructLikedContent(AggregationOutput aggregate, PrintWriter output, boolean countOnly){
		int rowCount = 0;
		int productCount = 0;
		String tableData = " ";
		String pageContent = " ";
		
		output.println("<h1> Grouped By - City </h1>");		
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				
				rowCount++;
				tableData = "<tr><td>City: "+bobj.getString("City")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (productCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(productCount)+"</br>"
							+   "Rating: "+rating.get(productCount)+"</br>"
							+	"Review: "+productReview.get(productCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					productCount++;					
				}	
				
				//Reset product count
				productCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}




	public void constructGroupByCityContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly, boolean Minonly,boolean Top5) {
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
		
		output.println("<h1> Grouped By - City </h1>");		
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				// BasicDBList maxPrice = (BasicDBList) bobj.get("MaxPrice");

			
				rowCount++;

				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>City: "+bobj.getString("City")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>City: "+bobj.getString("City")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{

				tableData = "<tr><td>City: "+bobj.getString("City")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);


				while (reviewCount < productReview.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;		}			
				}	
				
				//Reset product count
				reviewCount =0;
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByProductCategoryContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Products Category </h1>");
		for (DBObject result : aggregate.results()) 
		{
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Product Category : "+bobj.getString("ProductCategory")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Product Category: "+bobj.getString("ProductCategory")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td>Product Category: "+bobj.getString("ProductCategory")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);

			    while (reviewCount < productReview.size()) 

			    	{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
							
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;		}			
				}
					
				//Reset review count
				reviewCount = 0;
					
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}


	public void constructGroupByProductContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly) {
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Products </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;



				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Products: "+bobj.getString("Product")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating

					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td>Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
						

					}

					reviewCount++;  }
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Product: "+bobj.getString("Product")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td>Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{

				tableData = "<tr><td>Product: "+bobj.getString("Product")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productReview.size()) {
					tableData = "<tr rowspan = \"3\"><td>Rating: "+rating.get(reviewCount)+"</br>"
								+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
								+ "Review: "+productReview.get(reviewCount)+"</td></tr>";
							
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}
					
				//Reset review count
				
					
				}

				reviewCount = 0;

		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}


	

	public void constructGroupByProductPriceContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Products Price </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;



				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Product Price: "+bobj.getString("ProductPrice")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Product Price: "+bobj.getString("ProductPrice")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td>Product Price: "+bobj.getString("ProductPrice")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}

			}

				
				//Reset product count
				reviewCount =0;
					
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByretailerNameContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Retailer Name </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;




				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Retailer Name: "+bobj.getString("retailerName")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Retailer Name: "+bobj.getString("retailerName")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{




				tableData = "<tr><td>Retailer Name: "+bobj.getString("retailerName")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	public void constructGroupByRetailerZipContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Retailer Zip Code </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Retailer Zip Code : "+bobj.getString("RetailerZip")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData ="<tr><td>Retailer Zip Code : "+bobj.getString("RetailerZip")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{

				tableData = "<tr><td>Retailer Zip Code : "+bobj.getString("RetailerZip")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	

			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByRetailerStateContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Retailer State </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Retailer State: "+bobj.getString("RetailerState")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Retailer State: "+bobj.getString("RetailerState")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td>Retailer State: "+bobj.getString("RetailerState")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByProductOnSaleContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Product On Sale </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Products On Sale: "+bobj.getString("ProductOnSale")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Products On Sale: "+bobj.getString("ProductOnSale")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{

				tableData = "<tr><td>Products On Sale: "+bobj.getString("ProductOnSale")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByManufacturerNameContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Manufacturer Name </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;



				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Manufacture Name : "+bobj.getString("ManufacturerName")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Manufacture Name : "+bobj.getString("ManufacturerName")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{

				tableData = "<tr><td>Manufacture Name : "+bobj.getString("ManufacturerName")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}

				}	
				
				//Reset product count
				reviewCount =0;
					
		}		
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByManufacturerRebateContent(AggregationOutput aggregate, PrintWriter output,boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Manufacturer Rebate </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;




				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>Manufacture Name  Rebate : "+bobj.getString("ManufacturerRebate")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>Manufacture Name  Rebate : "+bobj.getString("ManufacturerRebate")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{

				tableData = "<tr><td>Manufacture Name  Rebate : "+bobj.getString("ManufacturerRebate")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	public void constructGroupByuserIDContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - User ID </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;



				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>USER ID : "+bobj.getString("userID")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>USER ID : "+bobj.getString("userID")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td>USER ID : "+bobj.getString("userID")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}
				}	
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByuserAgeContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - User Age </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>USER AGE : "+bobj.getString("userAge")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td>USER AGE : "+bobj.getString("userAge")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td>USER AGE : "+bobj.getString("userAge")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByuserGenderContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - User Gender </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td>USER GENDER : "+bobj.getString("userGender")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData ="<tr><td>USER GENDER : "+bobj.getString("userGender")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else 
					
					{


				tableData = "<tr><td>USER GENDER : "+bobj.getString("userGender")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
					+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByuserOccupationContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - User Occupation </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;

				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td> USER OCCUPATION : "+bobj.getString("userOccupation")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td> USER OCCUPATION : "+bobj.getString("userOccupation")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td> USER OCCUPATION : "+bobj.getString("userOccupation")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
					+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}

				}	
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
	public void constructGroupByreviewRatingContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Review Rating </h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;

				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td> REVIEW RATING : "+bobj.getString("reviewRating")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td> REVIEW RATING : "+bobj.getString("reviewRating")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}

				else 
					
					{


				tableData = "<tr><td> REVIEW RATING : "+bobj.getString("reviewRating")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
					
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
					+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	

			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}

	public void constructGroupByreviewDateContent(AggregationOutput aggregate, PrintWriter output, boolean Maxonly,boolean Minonly){
		int rowCount = 0;
		int reviewCount = 0;
		String tableData = " ";
		String pageContent = " ";
		int MaxPrice = 0 ;
		int MinPrice = 0 ;
				
		output.println("<h1> Grouped By - Review Date</h1>");
		for (DBObject result : aggregate.results()) {
				BasicDBObject bobj = (BasicDBObject) result;
				BasicDBList productList = (BasicDBList) bobj.get("Product");
				BasicDBList productReview = (BasicDBList) bobj.get("Reviews");
				BasicDBList rating = (BasicDBList) bobj.get("Rating");
				BasicDBList productPrice = (BasicDBList) bobj.get("Price");
				
				rowCount++;


				if ( Maxonly == true ) {

					MaxPrice = bobj.getInt("MaxPrice");


				tableData = "<tr><td> REVIEW DATE : "+bobj.getString("reviewDate")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MaxPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MaxPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else if ( Minonly == true ) {

					MinPrice = bobj.getInt("MinPrice");


				tableData = "<tr><td> REVIEW DATE : "+bobj.getString("reviewDate")+"</td>&nbsp"
						+	"<td>Product Price : "+bobj.getString("MinPrice")+"</td></tr>";
						
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating



					while (reviewCount < productReview.size()) 


					{
						int Prodprice = (Integer)productPrice.get(reviewCount);

						if ( MinPrice == Prodprice ) 

						{

					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
							

					}

					reviewCount++;

					}
				}


				else 
					
					{


				tableData = "<tr><td> REVIEW DATE : "+bobj.getString("reviewDate")+"</td>&nbsp"
						+	"<td>Reviews Found: "+bobj.getString("Review Count")+"</td></tr>";
				
				pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
			    output.println(pageContent);
				
				//Now print the products with the given review rating
				while (reviewCount < productList.size()) {
					tableData = "<tr rowspan = \"3\"><td> Product: "+productList.get(reviewCount)+"</br>"
					+   "Product Price: "+productPrice.get(reviewCount)+"</br>"
							+   "Rating: "+rating.get(reviewCount)+"</br>"
							+	"Review: "+productReview.get(reviewCount)+"</td></tr>";
												
					pageContent = "<table class = \"query-table\">"+tableData+"</table>";		
					output.println(pageContent);
					
					reviewCount++;					
				}	
			}
				
				//Reset product count
				reviewCount =0;
					
		}	
		
		//No data found
		if(rowCount == 0){
			pageContent = "<h1>No Data Found</h1>";
			output.println(pageContent);
		}
		
	}
}