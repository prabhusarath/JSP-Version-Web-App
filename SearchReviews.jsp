<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
                    <head>             
    <%-- import any packages needed by the page --%>

    <%@ page import="java.io.*,javax.servlet.ServletException,javax.servlet.annotation.WebServlet,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,com.mongodb.MongoClient,com.mongodb.MongoException,com.mongodb.WriteConcern,com.mongodb.DB,com.mongodb.DBCollection,com.mongodb.BasicDBObject,com.mongodb.DBObject,com.mongodb.DBCursor,com.mongodb.ServerAddress,java.util.Arrays,java.util.List,java.util.Set,java.util.Date;" %>

</head>

<body>



    <%
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
                        
            DB db = mongo.getDB("CustomerReviews");
            
            DBCollection myReviews = db.getCollection("myReviews");

            
            // Find and display 
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put(searchField, searchParameter);

            DBCursor cursor = myReviews.find(searchQuery);
            output.println("Printing Values");
            output.println(cursor);
            
            while (cursor.hasNext()) {  %>
                     
                    <table cellspacing="2" cellpadding="2" border="1">

                        <%

                    BasicDBObject obj = (BasicDBObject) cursor.next();     %>       
                    
                    <tr>
                    <td> Product Name: </td>

                    <% String productName = obj.getString("productModelName"); %> 

                    <td><%=productName%></td>
                    </tr>
                    
                    <tr>
                    <td> User ID: </td>
                    <% String userName = obj.getString("userID"); %>
                    <td><%= userName%></td>
                    </tr>
                    
                    <tr>
                    <td> Review Rating: </td>
                    <% String reviewRating = obj.getString("reviewRating").toString(); %>
                    <td> <%=reviewRating%> </td>
                    </tr>
                    
                    <tr>
                    <td> Review Date: </td>
                    <% String reviewDate = obj.getString("reviewDate"); %>
                    <td> <%=reviewDate%> </td>
                    </tr>
                    
                    <tr>
                    <td> User Review : </td>
                    <% String reviewText = obj.getString("reviewText"); %>
                    <td> <%=reviewText%> </td>
                    </tr>

                    <tr>
                    <td> Manufacture Name  : </td>
                    <% String manf = obj.getString("ManufacturerName"); %>
                    <td> <%=manf%> </td>
                    </tr>

                    <tr>
                    <td> Retailer Zip : </td>
                    <% String retailerzip = obj.getString("RetailerZip"); %>
                    <td> <%=retailerzip %> </td>
                    </tr>

                    <tr>
                    <td> Retailer City : </td>
                    <% String retailercity = obj.getString("RetailerCity"); %>
                    <td> <%=retailercity%> </td>
                    </tr>

                    </table>
                    </body>
                    </html>




<%

            }

        } catch (MongoException e) {
        e.printStackTrace();
        }

         %>

         <form method="post" action="SearchReview.jsp">

            <table>

            <input type = "submit" value = "Go Back to Search Reviews">

            </table>

   
    
            


    
