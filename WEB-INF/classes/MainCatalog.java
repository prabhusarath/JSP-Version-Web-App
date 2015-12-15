
public class MainCatalog {
  // This would come from a database in real life.
  // We use a static table for ease of testing and deployment.
  // See JDBC chapters for info on using databases in
  // servlets and JSP pages.
  private static MainCatalogItem[] items =
    {
     new MainCatalogItem
        ("Play Station 3 - Limited Edition",
         "<I>Play Station 3 - Limited Edition</I> " ,
         "PS3 32GB " +
           "From Sony\n",
         150.00),

new MainCatalogItem
        ("Play Station 3 - Black Edition",
         "<I>Play Station 3 - Black Edition</I> " ,
         "PS3 32GB" +
           "by SONY\n",
         150.00),

new MainCatalogItem
        ("Play Station 3 - Yellow Edition",
         "<I>Play Station 3 - Yellow Edition</I> " ,
         "PS3 32GB" +
           "by SONY\n",
         150.00),

new MainCatalogItem
        ("Play Station 4 - BATMAN Edition",
         "<I>Play Station 4 - BATMAN Edition</I> " ,
         "PS4 32GB" +
           "by SONY\n",
         150.00),

new MainCatalogItem
        ("X Box Original",
         "<I>X Box Original</I> " ,
         "XBOX 32GB" +
           "by MICROSOFT\n",
         120.00),

new MainCatalogItem
        ("X Box 360",
         "<I>X Box 360</I> " ,
         "XBOX 32GB" +
           "by MICROSOFT\n",
         120.00),

new MainCatalogItem
        ("X Box One",
         "<I>X Box One</I> " ,
         "XBOX 32GB" +
           "by MICROSOFT\n",
         120.00),

new MainCatalogItem
        ("WII-1X",
         "<I>WII-1X</I> " ,
         "Red 64GB  \n" +
           "by NINTENDO\n",
         100.00),

new MainCatalogItem
        ("WII-2X",
         "<I>WII-2X</I> " ,
         "White 64GB  \n" +
           "by NINTENDO\n",
         100.00),

new MainCatalogItem
        ("WIIU",
         "<I>WII-U</I> " ,
         "Black 64GB  \n" +
           "by NINTENDO\n",
         100.00),

new MainCatalogItem
        ("Assasins Creed 4 - PS3",
         "<I>Assasins Creed 4 - PS3</I> " ,
         "1 CD  \n" +
           "by EA SPORTS\n",
         50.00),

new MainCatalogItem
        ("God Of War 3 - PS4",
        "<I>God Of War 3 - PS4</I> " ,
         "1 CD  \n" +
           "by Activision\n",
         50.00),

new MainCatalogItem
        ("Mario 8 Racing - WII",
         "<I>Mario 8 Racing - WII</I> " ,
         "1 CD  \n" +
           "by Take Two Interactive\n",
         50.00),

new MainCatalogItem
        ("All Stars Battle Royal - PSVITA",
         "<I>All Stars Battle Royal - PSVITA</I> " ,
         "1 CD  \n" +
           "by Activision\n",
         50.00),

new MainCatalogItem
        ("PS4 - Race Controller",
         "<I>PS4 - Race Controller</I> " ,
         " 1 Piece  \n" +
           "by SONY\n" ,
         80.00),


        new MainCatalogItem
        ("XBOXController",
         "<I>XBOXController</I> " ,
         " 1 Piece  \n" +
           "by MICROSOFT\n" ,
         80.00),

        new MainCatalogItem
        ("WII Controller -Special Edition",
         "<I>WII Controller -Special Edition</I> " ,
         " 1 Piece  \n" +
           "by NINTENDO\n" ,
         80.00)


        };


  public static MainCatalogItem getItem(String itemID) {
    MainCatalogItem item;
    if (itemID == null) {
      return(null);
    }
    for(int i=0; i<items.length; i++) {
      item = items[i];
      if (itemID.equals(item.getItemID())) {
        return(item);
      }
    }
    return(null);
  }  
}
               


