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


public class MainStatusPage extends HttpServlet{

                private static final long serialVersionUID = 1L;

			
           MongoClient mongo;
    
    public void init() throws ServletException{
        // Connect to Mongo DB
        mongo = new MongoClient("localhost", 27017);
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{


            String searchField = "Customerorderid";
            int searchParameter = Integer.parseInt(request.getParameter("searchParameter")); 

                            
            // if database doesn't exists, MongoDB will create it for you
            DB db = mongo.getDB("CSP595Tutorial");
                
            // If the collection does not exists, MongoDB will create it for you
            DBCollection myOrders = db.getCollection("myOrders");

            // Find and display 
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put(searchField, searchParameter);

            DBCursor cursor = myOrders.find(searchQuery);

            PrintWriter out = response.getWriter();
            //output.println(cursor);



            if(cursor.count() == 0){
                out.println(" Wrong Order Number - Check Your Order Number Please ");
            }else
            {

                out.println(" Order Status Has Been Confirmed. We Will Inform Once the Product Has Been Shipped");

                    } 

            }
            catch (MongoException e) {
                 e.printStackTrace();
                      }

                      //output.println("Order Suceesfully Deleted");


                  }


              }

/*
            
            while (cursor.hasNext())
             {
                //output.println(cursor.next());

                    

                    BasicDBObject obj = (BasicDBObject) cursor.next();              
                    
                    out.println("<tr>");
                    out.println("<td> Order ID : </td>");
                    int order = Integer.parseInt(obj.getString("Customerorderid"));
                    out.println("<td>" +order+ "</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td> First Name : </td>");
                    String fname = obj.getString("FirstName");
                    out.println("<td>" +fname+ "</td>");
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<td> Last Name : </td>");
                    String lname = obj.getString("LastName");
                    out.println("<td>" +lname+ "</td>");
                    out.println("</tr>");

                    out.println("<tr>");
                    out.println("<td> Product Name: </td>");
                    String productName = obj.getString("productName");
                    out.println("<td>" +productName+ "</td>");
                    out.println("</tr>");
                    
                    out.println("<tr>");
                    out.println("<td> Product Price: </td>");
                    String ProdPrice = obj.getString("productPrice");
                    out.println("<td>" + ProdPrice + "</td>");
                    out.println("</tr>");


                    

            }*/

            

                     
 
                    
             



                     

				 
        	
             


