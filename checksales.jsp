<html>
<head>
 <%@ page import="java.io.*, java.util.*" %>
</head>
<body>
<%

	String userid = request.getParameter("userid");
	String password = request.getParameter("password");


	 HashMap users = new HashMap();

	users.put("Eplay", "Eplay");

      if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }

        if(userid != "" &&
            password != "") 

        {

                String realpassword = (String)users.get(userid);

                if(realpassword != null &&
                    realpassword.equals(password)) {


                      %>

 
                    <h2><u>E-PLAY</u></h2>

                    <h2> Login Salaesman  Success </h2>   

                    <p><h4>Hello Salesman ,  </h4>

                    
                    <h4> Click Below to Login as Customer to create, update and Delete Orders </h4>

                    <form action= "index.jsp">
                    <hr>
                    <input type = "submit" value = "Login as Customer">
                    </form>

                    <h4> Click Below To Create Customer Accounts </h4>

                    <form action= "Loginc.jsp">
                    <hr>
                    <input type = "submit" value = "Create Customer Accounts">
                    </form>

                    <h4> Logout </h4>

                    <form action= "Logins.jsp">
                    <hr>
                    <input type = "submit" value = " Logout ">
                    </form>



                    <%

                            } 


                else 

                { 

                 %>

                <h2><u>E-PLAY</u></h2>

                    <h2> Login Salesman Failure  </h2> 

                    <h2> Please Login Again </h2>

                    <form action= "Logins.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Salesman Login ">
                    </form>

                 <% 
                   //out.println("Login Failure! Username or password is incorrect");
                
                }


        }  

        else 

        {


        %>

                    <h2> Login Salesman Failure  </h2> 

                    <h2> Please Provide All Details </h2>

                    <form action= "Logins.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Salesman Login ">
                    </form>
<%
        }
        //out.println("Login Failure!  You must supply a username and password");
%>



</body>
</html>