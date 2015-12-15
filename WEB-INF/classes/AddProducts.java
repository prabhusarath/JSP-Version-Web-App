import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set; 
import java.util.List;
import java.util.ArrayList;


public class AddProducts extends ManagerProducts {

   
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

            if (Products.containsKey(Prodname)) 
            {
            out.println("Product Already Exists");
            } 

            else 
            {
                List<String> newlist = new ArrayList<String>();
                newlist.add(Prodprice); 
                newlist.add(prodCategory); 
                newlist.add(manufac);
                Products.put(Prodname, newlist);
                
        //Products.put(Prodname, Prodprice);

        Set setOfKeys = Products.keySet();

        Iterator iterator = setOfKeys.iterator();

        //out.println("<h2> Products Succesfully Added </h2>");

        while (iterator.hasNext()) {

            String key = (String) iterator.next();
           ArrayList<String> value = (ArrayList<String>) Products.get(key);


                    out.println("<html>");
                    out.println("<head></head>");
                    out.println("<body>");
                    
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<td> Product Name: </td>");
                    out.println("<td>" +key+ "</td>");
                    out.println("<td></td>");
                    out.println("<td> ,Product Details: </td>");
                    out.println("<td>" +value+ "</td>");
                    out.println("</tr>");
                    out.println("</table>");
                    out.println("</body>");
                    out.println("</html>");
                }
          //  System.out.println("Key doesnot exists in the HashMap");
             } 

         }

             else

             {

                    out.println("Please Provide All Product Details ");

             }

        
                    out.println("<form action =\"AddProducts.html\">");
                    out.println("<input type =\"submit\" value=\"Add More Products\">");
                    out.println("</form>");

                    out.println("<form action =\"ManagerProducts\">");
                    out.println("<input type =\"submit\" value=\"Go to Manager Page\">");
                    out.println("</form>");

    
        
    } 
    
    /**
     * Actually shows the <code>HTML</code> result page
     */
    
    /** Handles the HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
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
