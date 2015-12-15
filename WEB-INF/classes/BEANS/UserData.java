package BEANS;

import java.io.*;


public class UserData implements Serializable
{
    User user;
    public static void add(User user, String filepath) throws IOException
    {
        File file = new File(filepath);
        PrintWriter out = new PrintWriter(
                new FileWriter(file, true));
        out.println(user.getEmailAddress()+ "|"
                    + user.getFirstName() + "|"
                    + user.getLastName()+ "|"
                    +user.getaddress()+ "|"
                    +user.getcity()+ "|"
                    +user.getstate()+ "|"
                    +user.getzip()+ "|"
                    +user.getcreditcardnumber()+"|"
                    +user.getcvv());        
        out.close();
    }
}
