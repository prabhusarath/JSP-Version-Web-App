package BEANS;
import java.io.*;
import java.util.*;

public class User implements Serializable
{
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String creditcardnumber;
    private String cvv;
    private String order;
    private Date date;
    
    public User()
    {
        firstName = "";
        lastName = "";
        emailAddress = "";
        address = "";
        city = "";
        state = "";
        zip = "";
        creditcardnumber = "";
        cvv = "";
        order="";

    }
    
    public User(String firstName, String lastName, String emailAddress, String address, String city, String state, String zip, String creditcardnumber, String cvv, String order, Date date)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.creditcardnumber = creditcardnumber;
        this.cvv = cvv;
        this.order = order;
        this.date = date;

    }

    public void setFirstName(String firstName)
    { 
        this.firstName = firstName;
    }

    public String getFirstName()
    { 
        return firstName; 
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getLastName()
    { 
        return lastName; 
    }
    
    public void setEmailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getEmailAddress()
    { 
        return emailAddress; 
    }
     public void setaddress(String address)
    {
        this.address = address;
    }

    public String getaddress()
    { 
        return address; 
    }
     public void setcity(String city)
    {
        this.city = city;
    }

    public String getcity()
    { 
        return city; 
    }
     public void setstate(String state)
    {
        this.state = state;
    }

    public String getstate()
    { 
        return state; 
    }
     public void setzip(String zip)
    {
        this.zip = zip;
    }

    public String getzip()
    { 
        return zip; 
    }
     public void setcreditcardnumber(String creditcardnumber)
    {
        this.creditcardnumber = creditcardnumber;
    }

    public String getcreditcardnumber()
    { 
        return creditcardnumber; 
    }
     public void setcvv(String cvv)
    {
        this.cvv = cvv;
    }

    public String getcvv()
    { 
        return cvv; 
    }
    public void setorder(String order)
    {
        this.order = order;
    }

    public String getorder()
    { 
        return order; 
    }
    public void setdate(Date date)
    {
        this.date = date;
    }

    public Date getdate()
    { 
        return date; 
    }
}
