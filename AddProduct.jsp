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

        String Prodname = request.getParameter("Prodname");
        String Prodprice = request.getParameter("Prodprice");
        String prodCategory = request.getParameter("productCategory");
        String manufac = request.getParameter("ManufacturerName");

         %>


            <%

            // Connect to Mongo DB
            MongoClient mongo = new MongoClient("localhost", 27017);
                        
            DB db = mongo.getDB("Manager");
            
            // If the collection does not exists, MongoDB will create it for you
            DBCollection myReviews = db.getCollection("Products");
            System.out.println("Collection Products selected successfully"); 

            
            BasicDBObject doc = new BasicDBObject("title", "MongoDB").
                append("productModelName", Prodname).
                append("productCategory", prodCategory).
                append("productPrice", Prodprice).
                append("ManufacturerName", manufac);
                
            myReviews.insert(doc);
            
            System.out.println("Document inserted successfully");
            
            // Find and display 
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("productModelName", Prodname );

            String tableData = " ";
            String pageContent = " ";

        %>

        <%

            DBCursor cursor = myReviews.find(searchQuery);
            output.println("Printing Values");
            output.println(cursor);
            
            while (cursor.hasNext()) {

                // output.println(cursor.next());

                tableData = "query-table<tr><td> Product Review Details "+cursor.next()+"</td>&nbsp</tr>";
                
                pageContent = "<table cellspacing=\"5\" cellpadding=\"5\" border=\"1\">"+tableData+"</table>"; 

               // <table cellspacing=\"5\" cellpadding=\"5\" border=\"1\">      
                output.println(pageContent);

            }


        }catch (MongoException e) {
        e.printStackTrace();
        }
        
        output.println("       ");
        output.println("Done");

         %>
                    <form action ="Add.jsp">
                    <input type ="submit" value="Add Products">
                    </form>

        <form action ="Del.jsp">
        <input type ="submit" value="Delete Products">
        </form>

        <form action ="Update.jsp">
        <input type ="submit" value="Update Products">
        </form>
        
        <form action ="AllProducts.jsp">
        <input type ="submit" value="Show All Products">
        </form>

        <form action ="Loginm.jsp">
        <input type ="submit" value="Logout">
        </form>

            </body>

            </html>


    
