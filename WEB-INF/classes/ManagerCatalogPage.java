import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public abstract class ManagerCatalogPage extends HttpServlet {
  private MainCatalogItem[] items;
  private String[] itemIDs;
  private String title;

  /** Given an array of item IDs, look them up in the
   *  Catalog and put their corresponding CatalogItem entry
   *  into the items array. The CatalogItem contains a short
   *  description, a long description, and a price,
   *  using the item ID as the unique key.
   *  <P>
   *  Servlets that extend CatalogPage <B>must</B> call
   *  this method (usually from init) before the servlet
   *  is accessed.
   */
  
  protected void setItems(String[] itemIDs) {
    this.itemIDs = itemIDs;
    items = new MainCatalogItem[itemIDs.length];
    for(int i=0; i<items.length; i++) {
      items[i] = MainCatalog.getItem(itemIDs[i]);
    }
  }

  /** Sets the page title, which is displayed in
   *  an H1 heading in resultant page.
   *  <P>
   *  Servlets that extend CatalogPage <B>must</B> call
   *  this method (usually from init) before the servlet
   *  is accessed.
   */
  
  protected void setTitle(String title) {
    this.title = title;
  }

  /** First display title, then, for each catalog item,
   *  put its short description in a level-two (H2) heading
   *  with the price in parentheses and long description
   *  below. Below each entry, put an order button
   *  that submits info to the OrderPage servlet for
   *  the associated catalog entry.
   *  <P>
   *  To see the HTML that results from this method, do
   *  "View Source" on KidsBooksPage or TechBooksPage, two
   *  concrete classes that extend this abstract class.
   */
  
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    if (items == null) {
      response.sendError(response.SC_NOT_FOUND,
                         "Missing Items.");
      return;
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
    out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#4076AB\">\n" +
                "<H1 ALIGN=\"CENTER\">" + title + "</H1>");
    MainCatalogItem item;
    for(int i=0; i<items.length; i++) {
      out.println("<HR>");
      item = items[i];
      // Show error message if subclass lists item ID
      // that's not in the catalog.
      if (item == null) {
        out.println("<FONT COLOR=\"RED\">" +
                    "Unknown item ID " + itemIDs[i] +
                    "</FONT>");
      } else {
        out.println();
        //String formURL1 = "SubmitReview.html";

        String formURL =
          "http://localhost/csj/MainOrderPage";
        // Pass URLs that reference own site through encodeURL.
        formURL = response.encodeURL(formURL);
       // formURL1 = response.encodeURL(formURL1);
        
        out.println
          ("<FORM ACTION=\"" + formURL + "\">\n" +
           "<INPUT TYPE=\"HIDDEN\" NAME=\"itemID\" " +
           "       VALUE=\"" + item.getItemID() + "\">\n" +
           "<H2>" + item.getShortDescription() +
           " ($" + item.getCost() + ")</H2>\n"  +
           "<P>\n<CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\" " +
           "VALUE=\"Delete Items\">\n" +
           "</CENTER>\n<P>\n</FORM>");
          out.println
          ("<FORM ACTION=\"" + "SubmitReview.html" + "\">\n" +
            "<P>\n<CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\" " +
           "VALUE=\"Add Items\">\n" +
           "</CENTER>\n<P>\n</FORM>");
          out.println
          ("<FORM ACTION=\"" + "SearchReviews.html" + "\">\n" +
            "<P>\n<CENTER>\n" +
           "<INPUT TYPE=\"SUBMIT\" " +
           "VALUE=\"Update Items\">\n" +
           "</CENTER>\n<P>\n</FORM>");
      }
    }
    out.println("<HR>\n</BODY></HTML>");
  }
}
