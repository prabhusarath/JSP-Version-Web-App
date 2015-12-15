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
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SubmitOrder1 extends HttpServlet {

	

	//static int orderid = 1001;
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
		
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{

			//Get the values from the form
			//String productName = request.getParameter("productName");
			//int productPrice = Integer.parseInt(request.getParameter("productPrice"));
			String FirstName = request.getParameter("FirstName");
			String LastName = request.getParameter("LastName");
			String EmailId = request.getParameter("EmailId");
			String CustomerAddress = request.getParameter("CustomerAddress");
			String CustomerCity = request.getParameter("CustomerCity");
			String CustomerState = request.getParameter("CustomerState");
			String CustomerZipCode = request.getParameter("CustomerZipCode");
			String CreditCardNumber = request.getParameter("CreditCardNumber");
			String SecurityNumber = request.getParameter("SecurityNumber");
			String phoneNumber = request.getParameter("phoneNumber");	
			//int Customerorderid = orderid++;
			int randomNum = ThreadLocalRandom.current().nextInt(1000, 2000 + 1);

								
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection myOrders = db.getCollection("myOrders");
			System.out.println("Collection myOrders selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "myOrders").
				append("FirstName", FirstName).
				append("LastName", LastName).
				append("EmailId", EmailId).
				append("CustomerAddress", CustomerAddress).
				append("CustomerCity", CustomerCity).
				append("CustomerState", CustomerState).
				append("CustomerZip Code", CustomerZipCode).
				append("CreditCardNumber", CreditCardNumber).
				append("SecurityNumber", SecurityNumber).
				append("phoneNumber", phoneNumber).
				append("Customerorderid", randomNum );


			myOrders.insert(doc);
	
			System.out.println("Document inserted successfully");
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head> Order Summary </head>");
			out.println("<body>");
			out.println("<h1> Your Order/Reference number is :"+ randomNum + "</h1>");
			

			DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			Calendar cal = Calendar.getInstance();
 
			out.println("<h2>Date Of Purchase : " + df.format(cal.getTime())+" </h2>");
			cal.add(Calendar.DATE, 15);
        	out.println("<h2> The Expected Date of Delivery :" +cal.getTime()+"</h2>");

        	out.println("<h3>\n\n\n **Manufacturer's 1 year waranty has been Included.   </h3>");
        	out.println("<h3>\n\n\n ** Retailers 45 - Days Return Policy Are Included For the Product. </h3>");
        	out.println("<h3>\n\n\n **Cancellations must be done 5 business days prior to the delivery date </h3>");

        	


			out.println("<form action=\"index.html\">");
			out.println("<input type=\"submit\" value=\"Continue Shopping\">");			
			out.println("</form>");				

/*
			out.println("<form method=\"get\" action=\"MainCancelPage\">");

			out.println("<table><tr><tr><td> Search By: </td><td>"); 
			out.println("<select name=\"searchField\">");
			out.println("<option value=\"Customerorderid\" selected> Customer Order Id </option></td></tr>");
			out.println("<tr><td> Order Number Please : </td><td><input type = \"text\" name = \"searchParameter\" size = \"20\"/></td></tr></table>");
			out.println("<input type = \"submit\"  value = \"Cancel  Order\"/></form>");
*/

			out.println("</body>");
			out.println("</html>");
			
			
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}

	
	public void destroy()	{
      // do nothing.
	}
	
}