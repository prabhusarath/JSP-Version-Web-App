<html>
<head>
  <title>Buy</title>
</head>
<body>
  <h1>Product Reviews</h1>
	
	<%!
		String productName = "";
		String imageLocation = " ";
		double productPrice = 0.0;		
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
		
	<form method="get" action="SubmitReviews"> 
		
		<fieldset>
			<legend>Product information:</legend>
			<img src = <%=imageLocation%> width = "200" height = "200" alt = "X Box Orginal">
			<table>
				<tr>
					<td> Product Name: </td>
					<td> <%=productName%> </td>
				</tr>
				
				<tr>
					<td> Product Price: </td>
					<td> <%=productPrice%> </td>
				</tr>
			</table>
		</fieldset>
		
		<fieldset>
			<legend>Reviews:</legend>
			<table>

<tr>
<td> Product Model Name: </td>
<td> 
<select name="productModelName">
<option value="XBoxOriginal" selected>X Box Original</option>
<option value="XBox360">X Box 360</option>
<option value="XBoxOne">X Box One</option>
<option value="PlayStation3-LimitedEdition">Play Station 3 - Limited Edition</option>
<option value="PlayStation3-BlackEdition">Play Station 3 - Black Edition</option>
<option value="PlayStation3-YellowEdition">Play Station 3 - Yellow Edition</option>
<option value="PlayStation4-BATMANEdition">Play Station 4 - BATMAN Edition</option>
<option value="AssasinsCreed4-PS3">Assasins Creed 4 - PS3</option>
<option value="GodOfWar3-PS4">God Of War 3 - PS4</option>
<option value="Mario8Racing-WII">Mario 8 Racing - WII</option>
<option value="AllStarsBattleRoyal-PSVITA">All Stars Battle Royal - PSVITA</option>
<option value="WII-1X">WII - 1X</option>
<option value="WII-2X">WII - 2X</option>
<option value="WII-U">WII - U</option>
<option value="PS4-RaceController">PS4 - Race Controller</option>
<option value="X_BOXHandheld">XBOXController</option>
<option value="WIIController-SpecialEdition">WII Controller -Special Edition</option>
</td>
</tr>

<tr>
<td> Product Category: </td>
<td> 
<select name="productCategory">
<option value="Console" selected>Console</option>
<option value="Games" selected>Games</option>
<option value="Accessories">Accessories</option>
</td>
</tr>

<tr>
<td> Product Price: </td>
<td><input type = "text" name = "productPrice" size = 10/> </td>
</tr>

<tr>
<td> Retailer Name: </td>
<td> 
<select name="retailerName">
<option value="EPLAY" selected>EPLAY</option>
<option value="GameSpeed">GameSpeed</option>
</td>
</tr>

<tr>
<td> RetailerZip: </td>
<td><input type = "text" name = "RetailerZip" size = 10/> </td>
</tr>

<tr>
<td> Retailer City: </td>
<td> 
<select name="RetailerCity">
<option value="Chicago" selected>Chicago</option>
<option value="Houston">Houston</option>
<option value="New York City">New York City</option>
<option value="Los Angeles">Los Angeles</option>
</tr>

<tr>
<td> Retailer State: </td>
<td> 
<select name="RetailerState">
<option value="New York" selected>New York</option>
<option value="California">California</option>
<option value="Illinois">Illinois</option>
<option value="Texas">Texas</option>
</tr>

<tr>
<td> Product On Sale : </td>
<td> <input type = "radio" name = "ProductOnSale" value = "Yes"/> Yes  <input type = "radio" name = "ProductOnSale" value = "No"/> No </td>
</tr>

<tr>
<td> Manufacturer Name : </td>
<td> 
<select name="ManufacturerName">
<option value="SONY" selected>SONY</option>
<option value="MICROSOFT">MICROSOFT</option>
<option value="Nintendo">Nintendo</option>
<option value="ElectronicArts">Electronic Arts</option>
<option value="Activision">Activision</option>
<option value="TakeTwoInteractive">Take-Two Interactive</option>
</tr>

<tr>
<td> Manufacturer Rebate: </td>
<td> <input type = "radio" name = "ManufacturerRebate" value = "Yes"/> Yes  <input type = "radio" name = "ManufacturerRebate" value = "No"/> No </td>
</tr>


<tr>
<td> User ID: </td>
<td><input type = "text" name = "userID" size = 10/> </td>
</tr>

<tr>
<td> User Age: </td>
<td><input type = "text" name = "userAge" size = 10/> </td>
</tr>

<tr>
<td> User Gender: </td>
<td> <input type = "radio" name = "userGender" value = "Male"/> Male  <input type = "radio" name = "userGender" value = "Female"/> Female </td>
</tr>

<tr>
<td> User Occupation: </td>
<td><input type = "text" name = "userOccupation" size = 20/> </td>
</tr>

<tr>
<td> Review Rating: </td>
<td>
<select name="reviewRating">
<option value="1" selected>1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>  
</td>
</tr>

<tr>
<td> Review Date: </td>
<td><input type = "date" name = "reviewDate" size = 10/> </td>
</tr>

<tr>
<td> Review Text: </td>
<td><textarea name="reviewText" rows="4" cols="50"> </textarea></td>
</tr>

</table>
				
			<br><br>
			<input type="submit" value="Submit Review">
			
		</fieldset>
		
	</form>
		
	
</body>
</html>