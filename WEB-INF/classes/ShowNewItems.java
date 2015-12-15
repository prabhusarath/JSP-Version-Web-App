

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

public class ShowNewItems extends HttpServlet {

  Map Items = new HashMap();

    public void init()
    {
                Items.put("PlayStation_2", "100");
                Items.put("PS3", "100");
                Items.put("PS4", "100");
                Items.put("XBox2", "100");
                Items.put("XBox3", "100");
                Items.put("XBox4", "100");
                Items.put("WII-1X", "100");
                Items.put("WII-2X", "100");
                Items.put("WIIU", "100");
                Items.put("EASPORTS", "100");
                Items.put("Activision", "100");
                Items.put("TakeTwoInteractive", "100");
    }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    String newItem = request.getParameter("newItem");
    String newdesc = request.getParameter("newdesc");
    String newprice = request.getParameter("newPrice");


    if ((newdesc != null) && (newprice != null)) {
      
      Items.put(newdesc,newprice);

      }

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String title = "New Items Added";
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1>" + title + "</H1>");
      out.println("<UL>");
      out.println(" <LI> Item Number :" + newItem  );
      out.println(" <LI> Item Description :" + newdesc );
      out.println(" <LI> Item Price :" + newprice );
      out.println("</UL>");
      out.println("</BODY></HTML>");

      Set setOfKeys = Items.keySet();
      Iterator iterator = setOfKeys.iterator();
      while (iterator.hasNext()) {
      
      String key = (String) iterator.next();
      String value = (String)Items.get(key);
      out.println("Key: "+ key+", Value: "+ value);    

      }
    }

}
