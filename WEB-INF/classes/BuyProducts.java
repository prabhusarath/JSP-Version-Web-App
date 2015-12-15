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
import java.util.Date;

public class BuyProducts extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	String productName = " ";
	String imageLocation = " ";
	int productPrice = 0 ;
	
	public void init(){
		//Connect to Mongo DB
		MongoClient mongo = new MongoClient("localhost", 27017);
						
		// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("CustomerReviews");
		
		DBCollection myReviews = db.getCollection("myReviews");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
						
		try{
			//Get the values from the form
			if (request.getParameter("PlayStation3-Red") != null){
				productName = "PlayStation3-Red";
				imageLocation = "images/ps2l.jpg";
				productPrice = 150;
			}else if (request.getParameter("PlayStation3-Black") != null){
				productName = "PlayStation3-Black";
				imageLocation = "images/img_PlayStation3.jpg";
				productPrice = 150;
			}else if (request.getParameter("PlayStation3-Yellow") != null){
				productName = "PlayStation3-Yellow";
				imageLocation = "images/ps2y.jpg";
				productPrice = 150;
			}else if (request.getParameter("PlayStation4-Batman") != null){
				productName = "PlayStation4-Batman";
				imageLocation = "images/ps4b.jpg";
				productPrice = 150;
			}else if (request.getParameter("XBox_Original") != null){
				productName = "X Box Original";
				imageLocation = "images/xbox1.jpg";
				productPrice = 120;
			}else if (request.getParameter("XBox_360") != null){
				productName = "X Box 360";
				imageLocation = "images/xbox360.jpg";
				productPrice = 120;
			}else if (request.getParameter("XBox_One") != null){
				productName = "X Box One";
				imageLocation = "images/xboxg.jpg";
				productPrice = 120;
			}else if (request.getParameter("WII1") != null){
				productName = "WII1";
				imageLocation = "images/wii1.jpg";
				productPrice = 100;
			}else if (request.getParameter("WII2") != null){
				productName = "WII2";
				imageLocation = "images/wii2.jpg";
				productPrice = 100;
			}else if (request.getParameter("WII3") != null){
				productName = "WII3";
				imageLocation = "images/wiiu.jpg";
				productPrice = 100;
			}else if (request.getParameter("Assasins_4") != null){
				productName = "Assasins_4";
				imageLocation = "images/ps3.jpg";
				productPrice = 50;
			}else if (request.getParameter("God_3") != null){
				productName = "God_3";
				imageLocation = "images/ps4.jpg";
				productPrice = 50;
			}else if (request.getParameter("Mario_8") != null){
				productName = "Mario_8";
				imageLocation = "images/Mario-8-WiiU.jpg";
				productPrice = 50;
			}else if (request.getParameter("PS_Vita") != null){
				productName = "PS_Vita";
				imageLocation = "images/psvista.jpg";
				productPrice = 50;
			}else if (request.getParameter("PS4Controller") != null){
				productName = "PS4Controller";
				imageLocation = "images/a1.jpg";
				productPrice = 80;
			}else if (request.getParameter("XBOXController") != null){
				productName = "XBOXController";
				imageLocation = "images/a2.jpg";
				productPrice = 80;
			}else if (request.getParameter("WIICONTROLLER") != null){
				productName = "WIICONTROLLER";
				imageLocation = "images/a3.jpg";
				productPrice = 80;
			}
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Quick Buy</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Place Order</h1>");							
			out.println(" <h3>" +productName+ "</h3> ");
			
			out.println(" <form method=\"get\" action=\"SubmitOrder\">");
			out.println("<fieldset>");
			out.println("<legend>Product information:</legend>");
			out.println("<img src = \" "+imageLocation + " \" width = \"200\" height = \"200\" alt = \"Product Image\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Name:</td>");
			out.println("<td> <input type=\"text\" name=\"productName\" value= \'"+productName+"\' readonly> </td>");         
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td>Product Price: </td>");
			out.println("<td> <input type=\"text\" name=\"productPrice\" value= "+productPrice+" readonly> </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</fieldset>");
		
			out.println("<fieldset>");
			out.println("<table>");
			out.println("<tr> <td colspan=\"2\"><b>Customer Information</b></td></tr>");
        	out.println("<tr> <td>First Name</td><td><input type=\"text\" name=\"FirstName\" size=\"20\"/></td></tr> ");
        	out.println("<tr> <td>Last Name</td><td><input type=\"text\" name=\"LastName\" size=\"30\" /></td> </tr>" );
        	out.println("<tr><td>Phone Number </td><td><input type=\"text\" name=\"phoneNumber\" size=\"10\"/></td></tr>");
        	out.println("<tr> <td>Email</td><td><input type=\"text\" name=\"EmailId\" size=\"30\" /></td> </tr> ");
        	out.println("<tr><td colspan=\"2\"><hr /></td></tr><tr><td colspan=\"2\"><b>Address Information</b></td></tr><tr>");
        	out.println("<td>Address</td><td><input type=\"text\" name=\"CustomerAddress\" size=\"50\" /></td></tr>");
        	out.println("<tr><td>City</td><td><input type=\"text\" name=\"CustomerCity\" size=\"30\" /></td></tr>");
        	out.println("<tr><td>State</td><td><input type=\"text\" name=\"CustomerState\" size=\"15\">ZIP :<input type=\"text\" name=\"CustomerZipCode\" size=\"5\"/></td></tr>");
        	out.println("<hr><tr><td colspan=\"2\"><b>Credit Card Information</b></td></tr>");
        	out.println("<tr><td>Credit Card No :</td><td><input type=\"text\" name=\"CreditCardNumber\" size=\"16\"/></td></tr>");
        	out.println("<tr><td>CVV :</td><td><input type=\"text\" name=\"SecurityNumber\" size=\"5\"/></td></tr>");			
			out.println("</table>");
			out.println("<br><br>");
			out.println("<input type=\"submit\" value=\"Proceed to Checkout\">");			
			out.println("</fieldset>");		
			out.println("</form>");	
			out.println("</body>");
			out.println("</html>");
						
	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	}
}