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
java.util.Date,java.util.Calendar,java.util.concurrent.TimeUnit;"  %>



<%
        response.getWriter().append("Served at: ").append(request.getContextPath());
        
        response.setContentType("text/html");
        
        PrintWriter output = response.getWriter();
        
        output.println("Started");
        output.println("       ");  %>


<%
			

			String orderid = request.getParameter("orderid");

			MongoClient mongo = new MongoClient("localhost", 27017);
                        
            DB db = mongo.getDB("Orders");
            
            // If the collection does not exists, MongoDB will create it for you
            DBCollection myReviews = db.getCollection("order");
            System.out.println("Collection order selected successfully");

            int ord = Integer.parseInt(orderid);

            BasicDBObject search = new BasicDBObject();
            search.put("order",ord);


            String tableData = " ";
            String pageContent = " ";

            DBCursor cursor = myReviews.find(search);
            output.println("Printing Values");
            output.println(cursor);

              if (cursor.hasNext()) {   %>

 

                <table cellspacing="2" cellpadding="2" border="1">

                        <%

                    BasicDBObject obj = (BasicDBObject) cursor.next();     %>     
                    
                    <tr>
                    <td> Product Name: </td>

                    <% String order = obj.getString("order").toString(); %> 

                    <td><%=order%></td>
                    </tr>
                    
                    <tr>
                    <td> First Name : </td>
                    <% String userName = obj.getString("FirstName"); %>
                    <td><%= userName%></td>
                    </tr>
                        
                    <tr>
                    <td> Last Name : </td>
                    <% String reviewDate = obj.getString("LastName"); %>
                    <td> <%=reviewDate%> </td>
                    </tr>
                    
                    <tr>
                    <td> Email Id  : </td>
                    <% String reviewText = obj.getString("EmailId"); %>
                    <td> <%=reviewText%> </td>
                    </tr>

                    <tr>
                    <td> Address  : </td>
                    <% String Add = obj.getString("Address"); %>
                    <td> <%=Add%> </td>
                    </tr>

                    <tr>
                    <td> Retailer City : </td>
                    <% String city = obj.getString("City"); %>
                    <td> <%= city %> </td>
                    </tr>

                    <tr>
                    <td> Retailer State : </td>
                    <% String state = obj.getString("State"); %>
                    <td> <%=state%> </td>
                    </tr>

                     <tr>
                    <td> Retailer Zip : </td>
                    <% String Z = obj.getString("Zip"); %>
                    <td> <%=Z%> </td>
                    </tr>
  

   					<tr>
                    <td> Order Date  : </td>
                    <% String orderdate = obj.getString("date"); %>
                    <td> <%=orderdate%> </td>
                    </tr>


                    <tr>
                    	<td>
                    <h2> Checking for Order Cancellation Policy </h2>
                	</td>
                    </tr>

                    <tr>
                    <td> Orginal Order Date  : </td>
                    <% Date o = obj.getDate("date"); %>
                    <td> <%= o %> </td>
                    </tr>

                    <tr>
                    <td> Cancellation  Date : </td>
                    <% Date d = new Date(); %>
                    <td> <%= d %> </td>
                    </tr>

                    <tr>
                    <td> Difference Between Order and Cancellation Dates : </td>
                    <% long diff = d.getTime() - o.getTime(); %>
                    <% int conf = (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); %>
                    <td> <%= conf  %> </td>
                    </tr>

                   </table>

<%				


if ( conf < 10 )

{

				myReviews.remove(search); 
				output.println("<H2>Removing Order</h2>");
                output.println("<h2>Order Deletion Done</h2>");

}

else
{

	output.println("<H2> Order Cannot Be Deleted , Sorry Its has Been Shipped . </h2>");


}
                

   }

   else

   {
	

	output.println("<H2> Wrong Order Number . </h2>");

}



%>


<h2> Go Back to Shopping Page  </h2>

	<form action ="index.jsp">
    <input type="submit" name="index" value="Go to Main Page">
    </form>
 

</body>
</html>
