<html>
<head>
  <title>Buy</title>
  
</head>
<body>
	<link rel="stylesheet" href="css/styles.css">
  <h1>Place Order</h1>
 
	<%!
		String productName = "";
		String imageLocation = " ";
		int productPrice = 0;
		
	%>
	<%
		if (request.getParameter("PlayStation3-Red") != null){
				productName = "PlayStation3-Red";
				imageLocation = "images/ps2l.jpg";
				productPrice = 150;
			}else if (request.getParameter("PlayStation3-Black") != null){
				productName = "PlayStation3-Black";
				imageLocation = "images/img_PlayStation3.jpg";
				productPrice = 150;
			}else if (request.getParameter("PlayStation3-Yellow") != null){
				productName = "PlayStation3-Yellow";
				imageLocation = "images/ps2y.jpg";
				productPrice = 150;
			}else if (request.getParameter("PlayStation4-Batman") != null){
				productName = "PlayStation4-Batman";
				imageLocation = "images/ps4b.jpg";
				productPrice = 150;
			}else if (request.getParameter("XBox_Original") != null){
				productName = "X Box Original";
				imageLocation = "images/xbox1.jpg";
				productPrice = 120;
			}else if (request.getParameter("XBox_360") != null){
				productName = "X Box 360";
				imageLocation = "images/xbox360.jpg";
				productPrice = 120;
			}else if (request.getParameter("XBox_One") != null){
				productName = "X Box One";
				imageLocation = "images/xboxg.jpg";
				productPrice = 120;
			}else if (request.getParameter("WII1") != null){
				productName = "WII1";
				imageLocation = "images/wii1.jpg";
				productPrice = 100;
			}else if (request.getParameter("WII2") != null){
				productName = "WII2";
				imageLocation = "images/wii2.jpg";
				productPrice = 100;
			}else if (request.getParameter("WII3") != null){
				productName = "WII3";
				imageLocation = "images/wiiu.jpg";
				productPrice = 100;
			}else if (request.getParameter("Assasins_4") != null){
				productName = "Assasins_4";
				imageLocation = "images/ps3.jpg";
				productPrice = 50;
			}else if (request.getParameter("God_3") != null){
				productName = "God_3";
				imageLocation = "images/ps4.jpg";
				productPrice = 50;
			}else if (request.getParameter("Mario_8") != null){
				productName = "Mario_8";
				imageLocation = "images/Mario-8-WiiU.jpg";
				productPrice = 50;
			}else if (request.getParameter("PS_Vita") != null){
				productName = "PS_Vita";
				imageLocation = "images/psvista.jpg";
				productPrice = 50;
			}else if (request.getParameter("PS4Controller") != null){
				productName = "PS4Controller";
				imageLocation = "images/a1.jpg";
				productPrice = 80;
			}else if (request.getParameter("XBOXController") != null){
				productName = "XBOXController";
				imageLocation = "images/a2.jpg";
				productPrice = 80;
			}else if (request.getParameter("WIICONTROLLER") != null){
				productName = "WIICONTROLLER";
				imageLocation = "images/a3.jpg";
				productPrice = 80;
			}
	%>
		
	<h3><%=productName%></h3>
			
		<fieldset>
			<legend>Product information:</legend>
			<img src = <%=imageLocation%> width = "200" height = "200" alt = "Product Image">
			<table>
				<tr>
					<td> Product Name: </td>
					<td> <input type="text" name="productName" value='<%=productName%>' readonly> </td>         
				</tr>
				
				<tr>
					<td> Product Price: </td>
					<td> <input type="text" name="productPrice" value=<%=productPrice%> readonly> </td>
				</tr>
			</table>
		</fieldset>
		
		<form method="POST" action="Confirmation.jsp">
      <table cellpadding="0" cellspacing="5">
        <tr><td colspan="2"><b>Name Information</b></td></tr>
        <tr>
          <td>First Name</td><td><input type="text" name="FirstName" size="20"/></td>
        </tr>
        
        <tr>
          <td>Last Name</td><td><input type="text" name="LastName" size="30" /></td>
        </tr>
        <tr>
          <td>Email</td><td><input type="text" name="EmailId" size="30" /></td>
        </tr>
        <tr><td colspan="2"><hr /></td></tr>
        <tr><td colspan="2"><b>Address Information</b></td></tr>
        <tr>
          <td>Address</td><td><input type="text" name="Address" size="50" /></td>
        </tr>
        <tr>
          <td>City</td><td><input type="text" name="City" size="30" /></td>
        </tr>
        <tr>
          <td>State</td>
          <td>
            <input type="text" name="State" size="15">
           
            ZIP :<input type="text" name="Zip" size="5"/>
          </td>
        </tr>
        <hr>
 <tr><td colspan="2"><b>Credit Card Information</b></td></tr>
        <tr>
          <td>Credit Card No :</td><td><input type="text" name="CreditCardNumber" size="16"/></td>
        </tr>
 <tr>
      <td>CVV :</td><td><input type="text" name="Cvv" size="5"/></td>
 </tr>
        
        <tr><td colspan="2"><hr /></td></tr>
        <tr>
          <td>&nbsp;</td>
          <td>
            <input type="submit" value="Pay" />
            <input type="hidden" name="page_name" value="login" />
          </td>
        </tr>
      </table>
    </form>
		
		
</body>
</html>