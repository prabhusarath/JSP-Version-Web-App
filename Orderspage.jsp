<!doctype html public "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="BEANS.*"%>
<%@ page import = "java.util.*"%>

<html>
<head></head>
<body>

<link rel="stylesheet" href="css/styles.css">

<jsp:useBean id="cartitems1" scope="session" class="BEANS.Product" />
<jsp:useBean id="cartitems2" scope="session" class="BEANS.LineItem" />
<jsp:useBean id="cartitems3" scope="session" class="BEANS.Cart" />



<jsp:setProperty name="cartitems1" property="*" />
<jsp:setProperty name="cartitems2" property="*" />
<jsp:setProperty name="cartitems3" property="*" />


<% 
    int quantity = 1;


Product pd=null;
Cart cart=null;
String action=null;


String val = request.getParameter("item");
String val1 = request.getParameter("pdId");

action = request.getParameter("action");

if(action.equals("") || action==null)
	action="show";


 if(action.equals("addtocart"))
{
	val = request.getParameter("item");
	

   if (val != null)
   {
   pd = ProdData.finditem(val);
	ArrayList<LineItem> nlist=null;
   	cart = (Cart) session.getAttribute("cart");
        if (cart == null)
         {   cart = new Cart();
         nlist= new ArrayList<LineItem>();
         LineItem li= new LineItem();
			li.setProduct(pd);
			nlist.add(li);
		}
			else
			{
			
			nlist=cart.getItems();
			LineItem li= new LineItem();
			li.setProduct(pd);
			nlist.add(li);
			
			}
			cart.addItem(nlist);
			session.setAttribute("cart",cart);
      } 
}
else if(action.equals("show"))
{
	 //cart = (Cart) session.getAttribute("cart");

}
else if(action.equals("remove"))
{
	

    pd = ProdData.finditem(val1);
	ArrayList<LineItem> nlist=null;
   	cart = (Cart) session.getAttribute("cart");

			nlist=cart.getItems();
			LineItem li= new LineItem();
			li.setProduct(pd);
			cart.removeItem(li);
			session.setAttribute("cart",cart);
	
}
else
out.println("Hello");

%>

<h1>Your cart</h1>


<table border="1" cellpadding="5">
	<tr>
		<th>Product Code</th>
		<th>Description</th>
		<th>Quantity</th>
		<th>Amount</th>
		<th>Cancel</th>
	</tr>
	<% cart=(Cart)session.getAttribute("cart");
	ArrayList<LineItem> itemList=cart.getItems();
for(int i=0;i<itemList.size();i++)
	{
	Product plist=(itemList.get(i)).getProduct();
	%>

	<tr valign="top">
			<td>
				<%=plist.getCode()%>
			</td>
			<td>
				<%=plist.getDescription()%>

			</td>
			<td>
				1
			</td>
			<td>
				<%=plist.getPrice()%>
			</td>
			<td>
			<form action="Orderspage.jsp" name="pd"+ <%=plist.getCode()%> >
				<input type="hidden" name="pdId"  value=<%=plist.getCode()%> >
				<input type="hidden" name="action"  value="remove">
				<input type="submit" value="Remove Item">
			</form>


			</td>
		</tr>
<%}%>
	
	</tr>
</table>

<br>

<form action="index.jsp" method="post"><input type="submit" value=" Add More Products "></form>
<form action="checkout.jsp" method="post"><input type="submit" value="Checkout"></form>



</body>
</html>