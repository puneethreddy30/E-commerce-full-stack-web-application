<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="styles.css" type="text/css" />
<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--
pied, a free CSS web template by ZyPOP (zypopwebtemplates.com/)

Download: http://zypopwebtemplates.com/

License: Creative Commons Attribution
//-->
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />
<script>
function validateForm() 
{
    var x = document.forms["registerForm"]["user_name"].value;
	var y= document.forms["registerForm"]["pass_word"].value;
	var z= document.forms["registerForm"]["cpass_word"].value;
    if (x == null || x == "") {
        alert("Name must be filled out");
        return false;
    }
	if (y == null || y == "") {
        alert("Password must be filled out");
        return false;
    }
	/*if(y.equals(z){
        alert("Password and Confirm Password does not match");
        return false;*/
}
</script>
</head>
<body>
<section id="content">
	<form name="registerForm" action="RegisterToDB.jsp" onsubmit="return validateForm()">
<table align="center">
<th>Register Here!</th>
<tr>
<td>Enter Username<td>
<td><input type="text" name="user_name" ></td>
</tr>
<tr>
<td>Enter Password<td>
<td><input type="password" name="pass_word" ></td>
</tr>
<tr>
<td>Confirm Password<td>
<td><input type="password" name="cpass_word" ></td>
</tr>
<tr>
<tr>
<td></td>
<td><td><input type="submit">&nbsp&nbsp&nbsp <input type="reset"></td></td>
<td></td>
</tr>
<tr align="center"><td>
Already Registered. <a href="Login.jsp">Login Here!</a></td></tr>
</table>
</form>
</section></body>
</html>