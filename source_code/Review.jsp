<html>
<head>
<title>Write a review</title>
</head>

<body>
<div id="body">
		<section id="content">

<form action="SubmitReviews.jsp" method="get" >

<table>

<tr>
<td> Product Model Name: </td>
<td> 
<select name="productmodelname">


<option value="Apple Laptop" selected>Apple Laptop</option>
<option value="HP Spectre">HP Spectre</option>
<option value="Apple Iphone">Apple Iphone</option>


<option value="Samsung Galaxy">Samsung Galaxy</option>
<option value="Apple Ipad Mini">Apple Ipad Mini</option>
<option value="Samsung Tablet">Samsung Tablet</option>
<option value="Apple TV">Apple TV</option>

<option value="Samsung TV">Samsung TV</option>

</td>
</tr>

<tr>
<td> Product Category: </td>
<td> 
<select name="productcategory">
<option value="Laptops" selected>Laptops</option>
<option value="Mobiles" selected>Mobiles</option>
<option value="Telivisions">Telivisions</option>
<option value="Tablets">Tablets</option>
</td>
</tr>

<tr>
<td> Product Price: </td>
<td><input type = "text" name = "productprice" size = 10/> </td>
</tr>

<tr>
<td> Retailer Name: </td>
<td> 
<select name="retailername">
<option value="Apple" selected>Apple</option>
<option value="Samsung">Samsung</option>
<option value="Lenovo">Lenovo</option>
<option value="HP">HP</option>
</td>
</tr>

<tr>
<td> RetailerZip: </td>
<td><input type = "text" name = "retailerzip" size = 10/> </td>
</tr>

<tr>
<td> Retailer City: </td>
<td> 
<select name="retailercity">
<option value="Chicago" selected>Chicago</option>
<option value="Texas">Texas Dallas</option>
<option value="New York City">New York City</option>
<option value="Cinncinati">Cinncinati</option>
</tr>

<tr>
<td> Retailer State: </td>
<td> 
<select name="retailerstate">
<option value="New York" selected>Illinois</option>
<option value="California">Texas</option>
<option value="Illinois">New York</option>
<option value="Texas">Ohio</option>
</tr>

<tr>
<td> Product On Sale : </td>
<td> <input type = "radio" name = "productonsale" value = "Yes"/> Yes  <input type = "radio" name = "productonsale" value = "No"/> No </td>
</tr>

<tr>
<td> Manufacturer Name : </td>
<td> 
<select name="manufacturername">
<option value="Apple" selected>Apple</option>
<option value="Samsung">Samsung</option>
<option value="HP">HP</option>
</tr>

<tr>
<td> Manufacturer Rebate: </td>
<td> <input type = "radio" name = "manufacturerrebate" value = "Yes"/> Yes  <input type = "radio" name = "manufacturerrebate" value = "No"/> No </td>
</tr>


<tr>
<td> User ID: </td>
<td><input type = "text" name = "username" size = 10/> </td>
</tr>

<tr>
<td> User Age: </td>
<td><input type = "text" name = "userage" size = 10/> </td>
</tr>

<tr>
<td> User Gender: </td>
<td> <input type = "radio" name = "usergender" value = "Male"/> Male  <input type = "radio" name = "usergender" value = "Female"/> Female</td>
</tr>

<tr>
<td> User Occupation: </td>
<td><input type = "text" name = "useroccupation" size = 20/> </td>
</tr>

<tr>
<td> Review Rating: </td>
<td>
<select name="reviewrating">
<option value="1" selected>1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>  
</td>
</tr>

<tr>
<td> Review Date: </td>
<td><input type = "text" name = "reviewdate" size = 10/> </td>
</tr>

<tr>
<td> Review Text: </td>
<td><textarea name="reviewtext" rows="4" cols="50"> </textarea></td>
</tr>

<tr>
<td></td>
<td><input type = "submit" value = "Submit Review"/> </td>
</tr>

</table>
</form>
</section>