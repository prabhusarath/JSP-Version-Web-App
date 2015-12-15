<!DOCTYPE html>
<html lang="en"> 
<head>
  
  <title>Add Products</title>
  <link rel="stylesheet" href="css/login.css">
</head>
<body>

<h2> Hello Manager !!! </h2>

<form method="post" action="AddProduct.jsp">

<table>


<tr>
<td> Products Name : </td>
<td>
<input type = "text" name = "Prodname" size = "20"/>
</td>
</tr>

<tr>
<td> Products Price : </td>
<td>
<input type = "text" name = "Prodprice" size = "20"/>
</td>
</tr>

<tr>
<td> Product Category: </td>
<td> 
<select name="productCategory" size = "5"/>
<option value="Console" selected>Console</option>
<option value="Games" selected>Games</option>
<option value="Accessories">Accessories</option>
</td>
</tr>

<tr>
<td> Product Manufacturer : </td>
<td> 
<select name="ManufacturerName" size = "5"/>
<option value="SONY" selected>SONY</option>
<option value="Microsoft">Microsoft</option>
<option value="Nintendo">Nintendo</option>
<option value="EA SPORTS">EA SPORTS</option>
<option value="Activision">Activision</option>
<option value="TakeTwoInteractive">Take Two Interactive</option>


</tr>

</table>

<input type = "submit"  value = "Add Product"/>

</form>

</body>
</html>