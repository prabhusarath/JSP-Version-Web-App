<html>
<head>
 <%@ page import="java.io.*, java.util.*,java.util.HashMap,java.util.Map,javax.servlet.*,javax.servlet.http.*,javax.servlet.http.HttpSession,avax.servlet.*,javax.servlet.http.*,java.util.HashMap,java.util.Iterator,java.util.Map,java.util.Set,java.util.List,java.util.ArrayList;" %>

</head>
<body>

<%

	String userid = request.getParameter("userid");
	String password = request.getParameter("password");

    HashMap Customers = new HashMap();

    if (Customers.containsKey(userid)) {

            String realpassword = (String)Customers.get(userid);

      if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }

        Customers.put(userid,password);

        if(userid != "" &&
            password != "") 

        {

                if(realpassword != null &&
                    realpassword.equals(password)) {

                      %>

 
                    <h2><u>E-PLAY</u></h2>

                    Old User Problem... !!

                    <h2> Login Customer Success </h2>  
                    <h2> Welcome to the best Online Gaming Retailer </h2>  


                    <p><h4>Hello <%=userid%> ,  </h4>

                
                    <form action= "index.jsp">
                    <hr>
                    <input type = "submit" value = "Click to Shop">
                    </form>

                    <%

                            } 


                else 

                { 

                 %>

                <h2><u>E-PLAY</u></h2>

                    <h2> Login Customer Failure  </h2> 

                    <h2> Please Login Again </h2>

                    <form action= "Loginc.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Customer Login ">
                    </form>

                 <% 
                   //out.println("Login Failure! Username or password is incorrect");
                
                }


        }  

        else 

        {

        %>

                    <h2> Login Customer Failure  </h2> 

                    <h2> Please Provide All Details </h2>

                    <form action= "Loginc.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Customer Login ">
                    </form>
        <%
        }

    }

    else

    {

	Customers.put(userid,password);

      if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }


        if(userid != "" &&
            password != "") 

        {

                String realpassword = (String)Customers.get(userid);

                if(realpassword != null &&
                    realpassword.equals(password)) {

                    session.setAttribute("username",userid);

                      %>

 
                    <h2><u>E-PLAY</u></h2>

                    <h2> Login Customer Success </h2>  
                    <h2> Welcome to the best Online Gaming Retailer </h2>  


                    <p><h4>Hello <%=userid%> ,  </h4>

                        New User Details Login

                
                    <form action= "index.jsp">
                    <hr>
                    <input type = "submit" value = "Click to Shop">
                    </form>

                    <%

                            } 


                else 

                { 

                 %>

                <h2><u>E-PLAY</u></h2>

                    <h2> Login Customer Failure  </h2> 

                    <h2> Please Login Again </h2>

                    <form action= "Loginc.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Customer Login ">
                    </form>

                 <% 
                   //out.println("Login Failure! Username or password is incorrect");
                
                }


        }  

        else 

        {

        %>

                    <h2> Login Customer Failure  </h2> 

                    <h2> Please Provide All Details </h2>

                    <form action= "Loginc.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Customer Login ">
                    </form>
<%
        }
        //out.println("Login Failure!  You must supply a username and password");

        }
        //out.println("Login Failure!  You must supply a username and password");
%>
-->
</body>
</html>