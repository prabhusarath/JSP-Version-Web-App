<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

    <link rel="stylesheet" href="css/styles.css" type="text/css" />

</head>
<body>
    <%-- import any packages needed by the page --%>
    <%@ page import="BEANS.*,Manager.*,java.util.Date, java.io.*,java.util.Random,java.util.concurrent.ThreadLocalRandom,javax.servlet.*,javax.servlet.http.*,java.util.HashMap,java.util.Iterator,java.util.Map,java.util.Set,java.util.List,java.util.ArrayList,javax.servlet.http.HttpSession" %>

                    <h2><u>E-PLAY</u></h2>

                    <h2> Welcome Manager </h2>   

                    <p><h4> Please Choose any of the Options Below  </h4>



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