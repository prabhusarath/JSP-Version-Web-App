import java.util.Enumeration;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.util.Calendar;


 public class CheckOutPage extends HttpServlet{

        static int orderid = 1001;
        Calendar cal = Calendar.getInstance();
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, java.io.IOException {
            HttpSession session = request.getSession();
    	response.setContentType("text/html");
        java.io.PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Confirmation Page</title>");  
        out.println("\nThank you for your purchase!");
        out.println("</head>");
        out.println("<body>");
        out.println("Your Order/Reference number is :"+ orderid++);
        //  out.println("\nDate Of Purchase :"+cal.getTime());

        out.println("Date Of Purchase : " + (cal.get(Calendar.MONTH) + 1) + "-"
        + cal.get(Calendar.DATE) + "-" + cal.get(Calendar.YEAR));


        out.println("<table>");
        /* get the parameters and list them, with their values */
        Enumeration parameters = request.getParameterNames();
        
        while(parameters.hasMoreElements()) {
            String parameter = (String)parameters.nextElement();
            // get the parameter values
            String[] values = request.getParameterValues(parameter);
            if(values != null) {
                for(int i = 0; i < values.length; i++) {
                    out.println("<tr><td><b><font color=\"blue\">" + parameter + "</font></b></td>" +
                            "<td>" + values[i] + "</td></tr>");
                }
            }
        }
        out.println("</table>");
        cal.add(Calendar.DATE, 15);
        out.println("The Expected Date of Delivery :" +cal.getTime());
        out.println("\n\n\n **Cancellations must be done 5 business days prior to the delivery date");


        out.println("</body>");
        out.println("</html>");






        out.close();
        } 

            /** Handles the HTTP <code>GET</code> method.
            * @param request servlet request
            * @param response servlet response
            */
            protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                storeorder(request,response);
                processRequest(request, response);
                
                } 

            /** Handles the HTTP <code>POST</code> method.
            * @param request servlet request
            * @param response servlet response
            */
            protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
                 storeorder(request,response);
                 processRequest(request, response);
                
                }

                

            public void storeorder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException{
            MainHelperClass h = new MainHelperClass();
            MainItemOrder order;
            MainShoppingCart cart;
            ItemsList itm = null;
            HttpSession session = request.getSession();
            synchronized(session) {
            cart = (MainShoppingCart)session.getAttribute("MainshoppingCart");
             List itemsOrdered = cart.getItemsOrdered();
             Map<Integer, ItemsList> map = new HashMap<Integer, ItemsList>();
             List<String> val = new ArrayList<String>();
             for(int i=0; i<itemsOrdered.size(); i++)
            {
                itm = new ItemsList();
                order = (MainItemOrder)itemsOrdered.get(i);
                itm.iid = order.getItemID();
                itm.sd = order.getShortDescription();
                itm.noi = (order.getNumItems());
            }
                long miliSecondForDate1 = cal.getTimeInMillis();
                itm.msd = miliSecondForDate1;
                map.put(orderid, itm);
             
                
                 h.serial(map);
    

            }
            }
                
             

 
    } 
    class ItemsList{

                    String iid;
                    String sd;
                    int noi;
                    long msd;


                }
 class MainHelperClass {
    public void serial(Map<Integer, ItemsList> map)
    {

         try
                   {
                          FileOutputStream fos = new FileOutputStream("backup.dat");
                          ObjectOutputStream oos = new ObjectOutputStream(fos);
                          oos.writeObject(map);
                          oos.close();
                          fos.close();
                          System.out.printf("Serialized HashMap data is saved in backup.dat");
                   }catch(IOException ioe)
                    {
                          ioe.printStackTrace();
                          return;
                    }
    

    }


    public Map<Integer, ItemsList> dserial()
    {

        Map<Integer, ItemsList> map = null;
        try
                {
                 FileInputStream fis = new FileInputStream("backup.dat");
                 ObjectInputStream ois = new ObjectInputStream(fis);
                 map = (Map<Integer, ItemsList>) ois.readObject();
                 ois.close();
                 fis.close();
               }catch(IOException ioe)
                {
                    ioe.printStackTrace();
                    
                }catch(ClassNotFoundException c)
                    {
                    System.out.println("Class not found");
                    c.printStackTrace();
                    
                    }
    
                    return map;
    }


}  
 