import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/** Servlet that displays a list of items being ordered.
 *  Accumulates them in an ArrayList with no attempt at
 *  detecting repeated items. Used to demonstrate basic
 *  session tracking. Updated to Java 5.
 *  <P>
 *  Taken from Core Servlets and JavaServer Pages 2nd Edition
 *  from Prentice Hall and Sun Microsystems Press,
 *  http://www.coreservlets.com/.
 *  &copy; 2003 Marty Hall; may be freely used or adapted.
 */

@SuppressWarnings("unchecked")

public class DelItem extends HttpServlet {

  Map Items = new HashMap();

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

    
    String newdesc = request.getParameter("DelItem");

    if ((newdesc != null)) {
      
      if (Items.containsKey(newdesc)) 
      {
       String value = (String) Items.remove(newdesc);
       response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "Items Delted";
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1>" + title + "</H1>");
      out.println("<UL>");
      out.println(" <LI> Deleted ONE Object from HashMap: "+value  );
      out.println("</UL>");
      out.println("</BODY></HTML>");
      } 

      
      }
  }
}