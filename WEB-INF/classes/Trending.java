import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
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
import java.text.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Trending extends HttpServlet {
MongoClient mongo;

public void init() throws ServletException {
        mongo = new MongoClient("localhost", 27017);
}


public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 

{

        response.setContentType("text/html");

        PrintWriter output = response.getWriter();

        String pageHeading = " ";

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
                           +  "<li class=\"\"><a href=\"index.html\">Home</a></li>"
                            + "<li class = \"\"><a href=\"SubmitReview.html\">Write Review</a></li>"
                            + "<li class = \"start selected\"><a href=\"DataAnalytics.html\">Data Analytics</a></li>"
                           + "</ul>"
                           + "</nav>"
                           + "<div id=\"body\">"
                           + "<section id=\"review-content\">"
                           + "<article>"
                           + "<h3style=\"color:#DE2D3A;font-weight:700;\">" +pageHeading + "</h3>";

        output.println(myPageTop);

        DB db = mongo.getDB("CustomerReviews");
        DBCollection myReviews = db.getCollection("myReviews");

        try{


                DBObject match = null;
                BasicDBObject matchFields = new BasicDBObject();
                DBObject groupFields = null;
                DBObject group = null;
                DBObject projectFields = null;
                DBObject project = null;
                AggregationOutput aggregate = null;

                matchFields.put("reviewRating",5);
                match= new BasicDBObject("$match", matchFields);

                projectFields = new BasicDBObject("_id", 0);

                groupFields = new BasicDBObject("_id", 0);

                groupFields.put("_id", "$RetailerCity");
                groupFields.put("count", new BasicDBObject("$sum", 1));
                groupFields.put("productModelName", new BasicDBObject("$push", "$productModelName"));

                group = new BasicDBObject("$group", groupFields);


                projectFields.put("City", "$_id");
                projectFields.put("Review Count", "$count");
                projectFields.put("Product", "$productModelName");

                project = new BasicDBObject("$project", projectFields);

                aggregate = myReviews.aggregate(match,group,project);

                int rowCount = 0;
                int reviewCount=0;
                String tableData = " ";
                String pageContent = " ";
                int maxCount=0;
                int max;

                output.println("<h1> Grouped By - Trending Products In Different City with Highest Rating</h1>"); 

                 rowCount++;

                for (DBObject result : aggregate.results()) {
                        BasicDBObject bobj = (BasicDBObject) result;
                        ArrayList productList = (ArrayList) bobj.get("Product");

                        HashMap<String,Integer> map = new HashMap<String,Integer>();

                        for (int i=0; i<productList.size(); i++) {
                                map.put((String)productList.get(i),0);
                        }

                        for (int i=0; i<productList.size(); i++) {
                                map.put((String)productList.get(i),map.get((String)productList.get(i))+1);
                        } 

                        tableData = "<tr><td>City: "+bobj.getString("City")+"</td>&nbsp</tr>";

                        pageContent = "<table class = \"query-table\">"+tableData+"</table>";
                        output.println(pageContent);

                        Set itr = map.entrySet();
                        Iterator iterator = itr.iterator();

                        max = 0 ;

                        while(iterator.hasNext()) {
                          
                                Map.Entry loop = (Map.Entry)iterator.next();
                                
                           
                                if ( ((Integer)loop.getValue()) > max )
                                {

                                  max =  (Integer)loop.getValue();


                                }

                                if (loop.getValue().equals(max)) {

                                pageContent = "<table border=\"0\" class = \"query-table\">"+loop.getKey()+"</table>";
                                output.println(pageContent);

                                }
                              
                        }


                }

                if(rowCount == 0) {
                        pageContent = "<h1>No Data Found</h1>";
                        output.println(pageContent);
                }

        }catch (MongoException e) {
                e.printStackTrace();
        }

        String myPageBottom = "</article>"
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
}
