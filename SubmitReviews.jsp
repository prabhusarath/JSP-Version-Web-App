<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
    <%-- import any packages needed by the page --%>

    <%@ page import="java.io.*,javax.servlet.ServletException,
javax.servlet.annotation.WebServlet,
javax.servlet.http.HttpServlet,
javax.servlet.http.HttpServletRequest,
javax.servlet.http.HttpServletResponse,
com.mongodb.MongoClient,
com.mongodb.MongoException,
com.mongodb.WriteConcern,
com.mongodb.DB,
com.mongodb.DBCollection,
com.mongodb.BasicDBObject,
com.mongodb.DBObject,
com.mongodb.DBCursor,
com.mongodb.ServerAddress,
java.util.Arrays,
java.util.List,
java.util.Set,
java.util.Date;" %>



    <%
        response.getWriter().append("Served at: ").append(request.getContextPath());
        
        response.setContentType("text/html");
        
        PrintWriter output = response.getWriter();
        
        output.println("Started");
        output.println("       ");  %>


        
        <%

        try{
            //Get the values from the form
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
            String reviewText = request.getParameter("reviewText"); %>


            <%

            // Connect to Mongo DB
            MongoClient mongo = new MongoClient("localhost", 27017);
                        
            DB db = mongo.getDB("CustomerReviews");
            
            // If the collection does not exists, MongoDB will create it for you
            DBCollection myReviews = db.getCollection("myReviews");
            System.out.println("Collection myReviews selected successfully"); 

            
            BasicDBObject doc = new BasicDBObject("title", "MongoDB").
                append("productModelName", productModelName).
                append("productCategory", productCategory).
                append("productPrice", productPrice).
                append("retailerName", retailerName).
                append("RetailerZip", RetailerZip).
                append("RetailerCity", RetailerCity).
                append("RetailerState", RetailerState).
                append("ProductOnSale", ProductOnSale).
                append("ManufacturerName", ManufacturerName).
                append("ManufacturerRebate", ManufacturerRebate).
                append("userID", userID).
                append("userAge", userAge).
                append("userGender", userGender).
                append("userOccupation", userOccupation).
                append("reviewRating", reviewRating).
                append("reviewDate", reviewDate).
                append("reviewText", reviewText);
                
            myReviews.insert(doc);
            
            System.out.println("Document inserted successfully");
            
            // Find and display 
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("productModelName", productModelName);

            String tableData = " ";
            String pageContent = " ";

        %>

        <%

            DBCursor cursor = myReviews.find(searchQuery);
            output.println("Printing Values");
            output.println(cursor);
            
            while (cursor.hasNext()) {

                // output.println(cursor.next());

                tableData = "<tr><td> Product Review Details "+cursor.next()+"</td>&nbsp</tr>";
                
                pageContent = "<table class = \"query-table\">"+tableData+"</table>";       
                output.println(pageContent);

            }


        }catch (MongoException e) {
        e.printStackTrace();
        }
        
        output.println("       ");
        output.println("Done");

         %>

   
    
            <form method="post" action="SubmitReview.jsp">

            <table>

            <input type = "submit" value = "Go Back to Submit Reviews">

            </table>

            </body>

            </html>


    
