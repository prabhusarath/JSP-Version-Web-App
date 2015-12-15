
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class ManagerProducts extends HttpServlet {
   
     //public static Map Products = new HashMap();

	

     public static Map<String, List<String>> Products = new HashMap<String, List<String>>();


public List<String> PS3R = new ArrayList<String>();
public List<String> PS3B = new ArrayList<String>();
public List<String> PS3Y = new ArrayList<String>();
public List<String> PS3BM = new ArrayList<String>();
public List<String> XBOXO = new ArrayList<String>();
public List<String> XBOX360 = new ArrayList<String>();
public List<String> XBOXONE = new ArrayList<String>();
public List<String> WII1 = new ArrayList<String>();
public List<String> WII2 = new ArrayList<String>();
public List<String> WII3 = new ArrayList<String>();
public List<String> ASS4 = new ArrayList<String>();
public List<String> GOD3= new ArrayList<String>();
public List<String> M8 = new ArrayList<String>();
public List<String> PSV = new ArrayList<String>();
public List<String> PS4C = new ArrayList<String>();
public List<String> XBOXC = new ArrayList<String>();
public List<String> WIIC = new ArrayList<String>();

    public void init() {

    	PS3R.add("150"); PS3R.add("Console"); PS3R.add("Sony");
		PS3B.add("150"); PS3B.add("Console"); PS3B.add("Sony");
		PS3Y.add("150"); PS3Y.add("Console"); PS3Y.add("Sony");
		PS3BM.add("150"); PS3BM.add("Console"); PS3BM.add("Sony");

        XBOXO.add("120"); XBOXO.add("Console"); XBOXO.add("Microsoft");
        XBOX360.add("120"); XBOX360.add("Console"); XBOX360.add("Microsoft");
        XBOXONE.add("120"); XBOXONE.add("Console"); XBOXONE.add("Microsoft");

        WII1.add("100"); WII1.add("Console"); WII1.add("Nintendo");
		WII2.add("100"); WII2.add("Console"); WII2.add("Nintendo");
		WII3.add("100"); WII3.add("Console"); WII3.add("Nintendo");


		ASS4.add("50"); ASS4.add("Gaming"); ASS4.add("EA Sports");
		GOD3.add("50"); GOD3.add("Gaming"); GOD3.add("Take Two Interactive");
		M8.add("50"); M8.add("Gaming"); M8.add("EA SPORTS");
		PSV.add("50"); PSV.add("Gaming"); PSV.add("Activision");

		PS4C.add("80"); PS4C.add("Accessories"); PS4C.add("Microsoft");
		XBOXC.add("80"); XBOXC.add("Accessories"); XBOXC.add("Sony");
		WIIC.add("80"); WIIC.add("Accessories"); WIIC.add("Nintendo");



		Products.put("PlayStation3-Red", PS3R);
        Products.put("PlayStation3-Black", PS3B);
        Products.put("PlayStation3-Yellow", PS3Y);
        Products.put("PlayStation4-Batman", PS3BM);
        Products.put("XBox_Original", XBOXO);
        Products.put("XBox_360",  XBOX360);
        Products.put("XBox_One", XBOXONE);
        Products.put("WII1", WII1);
        Products.put("WII2", WII2);
        Products.put("WII3", WII3);
        Products.put("Assasins_4", ASS4);
        Products.put("God_3", GOD3);
        Products.put("Mario_8", M8);
        Products.put("PS_Vita", PSV);
        Products.put("PS4Controller", PS4C);
        Products.put("XBOXController", XBOXC);
        Products.put("WIICONTROLLER", WIIC);
/*

    	Products.put("PlayStation3-Red", "150");
        Products.put("PlayStation3-Black", "150");
        Products.put("PlayStation3-Yellow", "150");
        Products.put("PlayStation4-Batman", "150");
        Products.put("XBox_Original", "120");
        Products.put("XBox_360", "120");
        Products.put("XBox_One", "120");
        Products.put("WII1", "100");
        Products.put("WII2", "100");
        Products.put("WII3", "100");
        Products.put("Assasins_4", "50");
        Products.put("God_3", "50");
        Products.put("Mario_8", "50");
        Products.put("PS_Vita", "50");
        Products.put("PS4Controller", "80");
        Products.put("XBOXController", "80");
        Products.put("WIICONTROLLER", "80");  */


      
		
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {

    	response.setContentType("text/html");
    	java.io.PrintWriter out = response.getWriter();

    	Set setOfKeys = Products.keySet();

    	Iterator iterator = setOfKeys.iterator();

    	while (iterator.hasNext()) {

    		String key = (String) iterator.next();
    		ArrayList<String> value = (ArrayList<String>) Products.get(key);



    				out.println("<html>");
					out.println("<head> </head>");
					out.println("<body>");
					out.println("<table>");

					out.println("<tr>");
					out.println("<td> Product Name: </td>");
					out.println("<td>" +key+ "</td>");
					out.println("<td></td>");
					out.println("<td> , Product Details: </td>");
					out.println("<td>" +value+ "</td>");
					out.println("</tr>");
					out.println("</table>");

					out.println("</body>");
					out.println("</html>");
				}

 
		out.println("<html>");
        out.println("<head>");
        out.println("<title>Store Products</title>");  
        out.println("</head>");
        out.println("<body>");
 		out.println("<form action =\"AddProducts.html\">");
        out.println("<input type =\"submit\" value=\"Add  Products \">");
        out.println("</form>");
        out.println("<form action =\"DelProducts.html\">");
        out.println("<input type =\"submit\" value=\"Delete Products \">");
        out.println("</form>");
        out.println("<form action =\"UpdateProducts.html\">");
        out.println("<input type =\"submit\" value=\"Update Products \">");
        out.println("</form>");

        out.println("<form action =\"Loginm.html\">");
                    out.println("<input type =\"submit\" value=\"Logout\">");
                    out.println("</form>");
        out.println("</body>");
        out.println("</html>");

}




}

/*



































        
        try{
			//Get the values from the form
			if (request.getParameter("PlayStation3-Red") != null){
				productName = "PlayStation3-Red";
				productPrice = 80;
			}else if (request.getParameter("PlayStation3-Black") != null){
				productName = "PlayStation3-Black";
				
				productPrice = 300;
			}else if (request.getParameter("PlayStation3-Yellow") != null){
				productName = "PlayStation3-Yellow";
				
				productPrice = 500;
			}else if (request.getParameter("PlayStation4-Batman") != null){
				productName = "PlayStation4-Batman";
				
				productPrice = 60;
			}else if (request.getParameter("XBox_Original") != null){
				productName = "X Box Original";
				
				productPrice = 80;
			}else if (request.getParameter("XBox_360") != null){
				productName = "X Box 360";
				
				productPrice = 300;
			}else if (request.getParameter("XBox_One") != null){
				productName = "X Box One";
				
				productPrice = 500;
			}else if (request.getParameter("WII1") != null){
				productName = "WII1";
				
				productPrice = 80;
			}else if (request.getParameter("WII2") != null){
				productName = "WII2";
				
				productPrice = 300;
			}else if (request.getParameter("WII3") != null){
				productName = "WII3";
				
				productPrice = 500;
			}else if (request.getParameter("Assasins_4") != null){
				productName = "Assasins_4";
				
				productPrice = 80;
			}else if (request.getParameter("God_3") != null){
				productName = "God_3";
				
				productPrice = 300;
			}else if (request.getParameter("Mario_8") != null){
				productName = "Mario_8";
				
				productPrice = 500;
			}else if (request.getParameter("PS_Vita") != null){
				productName = "PS_Vita";
				
				productPrice = 500;
			}else if (request.getParameter("PS4Controller") != null){
				productName = "PS4Controller";
				
				productPrice = 300;
			}else if (request.getParameter("XBOXController") != null){
				productName = "XBOXController";
				
				productPrice = 500;
			}else if (request.getParameter("WIICONTROLLER") != null){
				productName = "WIICONTROLLER";
				
				productPrice = 500;
			}


			out.println(" <form method=\"get\" action=\"\">");
			out.println("<fieldset>");
			out.println("<legend> Update Product information:</legend>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>Product Name:</td>");
			out.println("<td> <input type=\"text\" name=\"productName\" value= \'"+productName+"\' > </td>");         
			out.println("</tr>");				
			out.println("<tr>");
			out.println("<td>Product Price: </td>");
			out.println("<td> <input type=\"text\" name=\"productPrice\" value= \'"+productPrice+"\' > </td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("<input type=\"submit\" value=\"Update Price\">");
			out.println("</form>");
			out.println("</fieldset>");

*/
