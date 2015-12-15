<html>
<head>
<title> Logout </title>  
</head>
<body>

	<%@ page import="java.io.IOException,java.io.PrintWriter,javax.servlet.ServletException,javax.servlet.http.HttpServlet,javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,javax.servlet.http.HttpSession;" %>

<%
                 
         request.getRequestDispatcher("Loginc.jsp").include(request, response);     
         session.invalidate();
       
          %>


<h1>You are successfully logged out!!! - Please Login to Continue </h1>  
</body>
</html>  
 
