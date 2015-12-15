<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Search Review</title>
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

                    <h2> Search Review</h2>

<form method="get" action="SearchReviews.jsp">
<table>

<tr>
<td> Search By: </td>
<td> 
<select name="searchField">
<option value="productModelName" selected>Product Name</option>
<option value="productCategory">Product Category</option>
<option value="retailerName">Retailer Name</option>
<option value="userID">User ID</option>
<option value="ManufacturerName">Manufacturer Name</option>
<option value="RetailerZip">Retailer Zip</option>
<option value="RetailerCity">Retailer City</option>
</td>
</tr>

<tr>
<td> Search Parameter: </td>
<td>
<input type = "text" name = "searchParameter" size = 20/>
</td>
</tr>

</table>

<td><input type = "submit" value = "Search Review"/> </td>
</form>

</article>
</section>

<div class="clear"></div>
        </div>
</body>

</html>