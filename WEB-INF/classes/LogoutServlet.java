


import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  



public class LogoutServlet extends HttpServlet {  


        protected void doGet(HttpServletRequest request, HttpServletResponse response)  
                                throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();             
              
            request.getRequestDispatcher("Loginc.jsp").include(request, response);  
              
            HttpSession session=request.getSession();  
            session.invalidate();  

            out.println("<html>");
            out.println("<head>");
            out.println("<title></title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>You are successfully logged out!!! - Please Login to Continue </h1>");  
            out.println("</body>");
            out.println("</html>");  
            out.close(); 

              
    }  
}  