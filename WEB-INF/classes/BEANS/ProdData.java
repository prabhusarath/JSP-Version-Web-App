package BEANS;

import java.io.*;
import java.util.*;
public class ProdData implements Serializable{


  public static Product finditem(String code){


  				 String code1;
    			 String description;
    			 double price;

  				Product p= new Product();
                String[][] catalogitems =  { 
                                                { "ps1", "Play Station 3 - Limited Edition", "150.00"},
                                                { "ps3", "Play Station 3 - Yellow Edition","150.00"},
                                                { "ps4", "Play Station 4 - BATMAN Edition" , "150.00"},
                                                { "xbox1", "X Box Original" , "120.00"},
                                                { "xbox3", "X Box One" , "120.00"}, 
                                                { "wii1", "WII-1X" , "100.00"}, 
                                                { "wii2", "WII-2X" , "100.00"}, 
                                                { "wii3", "WII-U" , "100.00"}, 
                                                { "games1", "Assasins Creed 4 - PS3" , "50.00"}, 
                                                { "games2", "God Of War 3 - PS4" , "50.00"}, 
                                                { "games3", "Mario 8 Racing - WII" , "50.00"}, 
                                                { "games4", "All Stars Battle Royal - PSVITA" , "50.00"}, 
                                                { "a1", "PS4 - Race Controller" , "80.00"},  
                                                { "a3", "WII Controller -Special Edition" , "80.00"},
                                                { "ps2", "Play Station 3 - Black Edition","150.00"},
                                                { "xbox2", "X Box 360" , "120.00"}, 
                                                { "a2", "XBOXController" , "80.00"}, 
                                               
                                                };

     for(int i = 0 ; i < catalogitems.length ; i++)
         {
            for(int j = 0 ;j < 3 ; j++)
                {
                    if (code.equals(catalogitems[i][0]))
                        {
                            code1 = catalogitems[i][0];
                            description=catalogitems[i][1] ;
                            price=Double.parseDouble(catalogitems[i][2]);
                            p.setCode(code1);
                            p.setDescription(description);
                            p.setPrice(price);
                        }

   
                }
        }

        return p;


    
    }


}