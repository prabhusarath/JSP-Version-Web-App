<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Write Review</title>
    <link rel="stylesheet" href="css/styles.css" type="text/css" />
</head>

<body>

	 <div id="container">
        
		<nav>
			<ul>
				<li class=""><a href="index.jsp">Home</a></li>
                <li class=""><a href="SubmitReview.jsp">Write Review</a></li>
                <li class=""><a href="SearchReview.jsp">Search Review</a></li>
			</ul>
		</nav>
		
        <div id="body">

            <section id="content">

                <article>

                    <h2> Add Product Review</h2>

                    <form method="get" action="SubmitReviews.jsp">




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

<tr>
<td> Submit Confirmation: </td>
<td><input type = "submit" value = "Submit Review"/> </td>
</tr>

</table>

 </form>

 

                </article>

            </section>

             <div class="clear"></div>
        </div>
</body>

</html>