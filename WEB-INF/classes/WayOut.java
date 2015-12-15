import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class WayOut extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
          response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>"); 
        out.println("</head>");
        out.println("<body>");
        out.println("<h2> Welcome User </h2>");
        out.println("Cancel Your Order");
        out.println("</body>");
        out.println("</html>");
        out.close();
    } 


}