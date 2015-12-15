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

public class SearchReviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter output = response.getWriter();
		
		output.println("Search Started");
		output.println("       ");
				
		try{
			//Get the values from the form
			String searchField = request.getParameter("searchField");
			String searchParameter = request.getParameter("searchParameter");			
			
			//Connect to Mongo DB
			MongoClient mongo = new MongoClient("localhost", 27017);
						
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CustomerReviews");
			
			DBCollection myReviews = db.getCollection("myReviews");

			PrintWriter out = response.getWriter();
			
			// Find and display 
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put(searchField, searchParameter);

			DBCursor cursor = myReviews.find(searchQuery);
			output.println("Printing Values");
			output.println(cursor);
			
			while (cursor.hasNext()) {
				//output.println(cursor.next());

					out.println("<html>");
					out.println("<head> </head>");
					out.println("<body>");
					out.println("<table>");

					BasicDBObject obj = (BasicDBObject) cursor.next();				
					
					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					String productName = obj.getString("productName");
					out.println("<td>" +productName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> User ID: </td>");
					String userName = obj.getString("userID");
					out.println("<td>" +userName+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Rating: </td>");
					String reviewRating = obj.getString("reviewRating").toString();
					out.println("<td>" +reviewRating+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> Review Date: </td>");
					String reviewDate = obj.getString("reviewDate");
					out.println("<td>" +reviewDate+ "</td>");
					out.println("</tr>");
					
					out.println("<tr>");
					out.println("<td> User Review : </td>");
					String reviewText = obj.getString("reviewText");
					out.println("<td>" +reviewText+ "</td>");
					out.println("</tr>");

					out.println("</table>");
					out.println("</body>");
					out.println("</html>");

			}

	    } catch (MongoException e) {
		e.printStackTrace();
	    }

	}
}