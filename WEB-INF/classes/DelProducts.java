import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set; 
import java.util.List;
import java.util.ArrayList;

public class DelProducts extends ManagerProducts {

   
    /** 
     * Initializes the servlet with some usernames/password
    */  
    public void init() {


		
    }

    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {


        response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();


        String Prodname = request.getParameter("Prodname");
        

         if(Prodname != null && Prodname.length() != 0) {


        if (Products.containsKey(Prodname)) {
        /**
        * remove() method deletes the element from HashMap.
        * It returns the deleted Object of the HashMap
        */

        ArrayList<String> value = (ArrayList<String>) Products.remove(Prodname);
        //String value =  (String)Products.remove(Prodname);
        out.println(" <h2>Deleted Product Name :"+Prodname+", Product Details"+ value + "</h2>");

        out.println(" <h2> Product Details  Deleted From HashMap </h2>");

        out.println("<h2> Products Succesfully Deleted </h2>");

        }

        else 
        {
           out.println("<h2> Products Does Not Exist </h2>"); 
        }
    }
    else
    {

        out.println("<h2> Please Provide Product Name </h2>");
    }


                    out.println("<form action =\"DelProducts.html\">");
                    out.println("<input type =\"submit\" value=\"Delete More Products\">");
                    out.println("</form>");

                    out.println("<form action =\"ManagerProducts\">");
                    out.println("<input type =\"submit\" value=\"Go to Manager Page\">");
                    out.println("</form>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 

    /** Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}

        /*
        out.println("<h2> Products Succesfully Added </h2>");

        Set setOfKeys = Products.keySet();

        Iterator iterator = setOfKeys.iterator();


        while (iterator.hasNext()) {

            String key = (String) iterator.next();
            String value = (String) Products.get(key);

                    out.println("<html>");
                    out.println("<head></head>");
                    out.println("<body>");
                    
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<td> Product Name: </td>");
                    out.println("<td>" +key+ "</td>");
                    out.println("<td></td>");
                    out.println("<td> ,Product Price: </td>");
                    out.println("<td>" +value+ "</td>");
                    out.println("</tr>");


                    out.println("</table>");

                    out.println("</body>");
                    out.println("</html>");
                }

                    out.println("<form action =\"AddProducts.html\">");
                    out.println("<input type =\"submit\" value=\"Add More Products\">");
                    out.println("</form>");

    
        
    } */
    
    /**
     * Actually shows the <code>HTML</code> result page
     */
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    
