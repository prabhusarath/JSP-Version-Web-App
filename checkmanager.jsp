<html>
<head>
 <%@ page import="java.io.*, java.util.*" %>
</head>
<body>


<%

	String userid = request.getParameter("userid");
    String password = request.getParameter("password");


	HashMap users = new HashMap();
	
     users.put("Manager", "Manager");

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

                    <h2> Login Manager Success </h2>   

                    <p><h4>Hello <%=userid%> ,  </h4>

                
                    <form action= "ManagerProducts.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Products">
                    </form>


                    <%

                            } 


                else 

                { 

                 %>
                

                <h2><u>E-PLAY</u></h2>

                    <h2> Login Manager Failure  </h2> 

                    <h2> Please Login Again </h2>

                    <form action= "Loginm.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Manager Login ">
                    </form>

                 <% 
                   //out.println("Login Failure! Username or password is incorrect");
                
                }


        }  

        else 

        {


        %>

                    <h2> Login Manager Failure  </h2> 

                    <h2> Please Provide All Details </h2>

                    <p><h4> Login Again Customer </h4>

                    <form action= "Loginm.jsp">
                    <hr>
                    <input type = "submit" value = "GO to Manager Login ">
                    </form>
<%
        }
        //out.println("Login Failure!  You must supply a username and password");
%>



</body>
</html>