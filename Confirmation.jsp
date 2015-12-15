<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
</head>
<body>
    <link rel="stylesheet" href="css/styles.css">
    <%-- import any packages needed by the page --%>
    <%@ page import="BEANS.*,java.util.Date, java.io.*,java.util.Random,java.util.concurrent.ThreadLocalRandom" %>
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
java.util.Date,java.text.SimpleDateFormat,java.util.Date,java.text.DateFormat,java.text.SimpleDateFormat,java.util.Calendar" %>

    <%!
        // declare a method for the page
        public void add(User user, String filename)
                throws IOException
        {
            PrintWriter out = new PrintWriter(
                    new FileWriter(filename, true));
            out.println(user.getEmailAddress()+ "|"
                    + user.getFirstName() + "|"
                    + user.getLastName()+ "|"
                    +user.getaddress()+ "|"
                    +user.getcity()+ "|"
                    +user.getstate()+ "|"
                    +user.getzip()+ "|"
                    +user.getcreditcardnumber()+ "|"
                    +user.getcvv()+ "|"
                    +user.getorder()+"|"+user.getdate());
            out.close();
        }
    %>

    <%

    int globalCount = ThreadLocalRandom.current().nextInt(5000, 6000 + 1);  // this is not thread-safe


        String firstName = request.getParameter("FirstName");
        String lastName = request.getParameter("LastName");
        String emailAddress = request.getParameter("EmailId");
        String address = request.getParameter("Address");
        String city = request.getParameter("City");
        String state = request.getParameter("State");
        String zip= request.getParameter("Zip");
        String creditcardnumber = request.getParameter("CreditCardNumber");
        String cvv= request.getParameter("Cvv");
        String order = Integer.toString(globalCount) ;
        Date date = new Date();
        String status = "Approved";


        ServletContext sc = getServletContext();
        String path = sc.getRealPath("/WEB-INF/Orders.txt");

        User user = new User(firstName, lastName, emailAddress,address,city,state,zip,creditcardnumber,cvv,order,date);

        // use the declared method
        this.add(user, path);

        DateFormat df = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
    
            // Connect to Mongo DB
            MongoClient mongo = new MongoClient("localhost", 27017);
                        
            DB db = mongo.getDB("Orders");
            
            // If the collection does not exists, MongoDB will create it for you
            DBCollection myReviews = db.getCollection("order");
            System.out.println("Collection order selected successfully"); 

            
            BasicDBObject doc = new BasicDBObject("title", "MongoDB").
                append("order", globalCount).
                append("FirstName", firstName).
                append("LastName", lastName).
                append("EmailId", emailAddress).
                append("Address", address).
                append("City", city).
                append("State", state).
                append("Zip", zip).
                append("CreditCardNumber", creditcardnumber).
                append("Cvv", cvv).
                append("date", date).append("status",status);


                     
            myReviews.insert(doc);
            
            System.out.println("Document inserted successfully");
            
        %>
            

    <h1>Thank You for shopping in EPLAY</h1>

    <p>ORDER Confirmation</p>

    <p>Order Reference Number : <%=globalCount%> </p>

    <table cellspacing="5" cellpadding="5" border="1">
        <tr>
            <td align="right">First name:</td>
            <td><%= user.getFirstName() %></td>
        </tr>
        <tr>
            <td align="right">Last name:</td>
            <td><%= user.getLastName() %></td>
        </tr>
        <tr>
            <td align="right">Email address:</td>
            <td><%= user.getEmailAddress() %></td>
        </tr>
         <tr>
            <td align="right">Address:</td>
            <td><%= user.getaddress() %></td>
        </tr>
         <tr>
            <td align="right">City:</td>
            <td><%= user.getcity() %></td>
        </tr>
         <tr>
            <td align="right">State:</td>
            <td><%= user.getstate() %></td>
        </tr>
         <tr>
            <td align="right">Zip Code:</td>
            <td><%= user.getzip() %></td>
        </tr>
        <tr>
            <td align="right">ORDER Number:</td>
            <td><%= user.getorder() %></td>
        </tr>
        <tr>
            <td align="right">Date and Time Of Purchase :</td>
            <td><%= new Date() %></td>
        </tr>


    </table>

    <h2> Date Of Purchase : <%= cal.getTime() %> </h2>
            
            <%

            cal.add(Calendar.DATE, 10);

            %>

            <h2> Cancellation Before : <%= cal.getTime() %> </h2>

            
   
            <%

            cal.add(Calendar.DATE, 5);

            %>

            <h2> The Expected Date of Delivery : <%= cal.getTime() %> </h2>

    
    <h3>**Manufacturer's 1 year waranty has been Included.   </h>
    <h3>** Retailers 45 - Days Return Policy Are Included For the Product. </h3>
    <h3>**Cancellations must be done 5 business days prior to the delivery date </h3>


    <form action ="index.jsp">
    <input type="submit" name="index" value="Go to Main Page">
    </form>

</body>
</html>