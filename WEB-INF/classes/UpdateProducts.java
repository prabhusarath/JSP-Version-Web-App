import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set; 
import java.util.List;
import java.util.ArrayList;

public class UpdateProducts extends ManagerProducts {

   
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
        String Prodprice = request.getParameter("Prodprice");
        String prodCategory = request.getParameter("productCategory");
        String manufac = request.getParameter("ManufacturerName");


        if(Prodname != null && Prodname.length() != 0 && Prodprice != null && Prodprice.length() != 0) {

        if (Products.containsKey(Prodname)) {

                List<String> newlist = new ArrayList<String>();
                newlist.add(Prodprice); 
                newlist.add(prodCategory); 
                newlist.add(manufac);
                Products.put(Prodname, newlist);

        out.println("<h2> Product :"+ Prodname + "Successfully Updated </h2>"); 
        
        }

        else 
        {
           out.println("<h2> Not an  Existing Product </h2>"); 
        }

    }

    else
        {
            out.println("Please Provide All Product Details ");

        }


                    out.println("<form action =\"UpdateProducts.html\">");
                    out.println("<input type =\"submit\" value=\"Update More Products\">");
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

