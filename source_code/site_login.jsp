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
function validateForm() {
    var x = document.forms["loginForm"]["user_name"].value;
	var y= document.forms["loginForm"]["pass_word"].value;
    if (x == null || x == "") {
        alert("Name must be filled out");
        return false;
    }
	if (y == null || y == "") {
        alert("Password must be filled out");
        return false;
    }
}
</script>

</head>
<body>

<section id="content">

	<form name="loginForm" action="LogintoDB.jsp"  onsubmit="return validateForm()">
	<fieldset>
<h2 align="center">Login Here !</h2>
	<table align="center">

<tr>
<td>Enter Username</td>
<td><input type="text" name="user_name" ></td>
</tr>
<tr>
<td>Enter Password</td>
<td><input type="password" name="pass_word" ></td>
</tr>

<tr>
<td>User Type</td>
<td><input type="radio" name="gender" value="User">User<br>
  <input type="radio" name="gender" value="Sales Manager">Sales Manager<br>
  <input type="radio" name="gender" value="Store Manager">Store Manager</td>
</tr>


<tr>

<td><td><input type="submit"></td></td>
</tr>
<tr >
<td>Not yet Registered? <a href="Register.jsp">Register Here!</a></td></tr><tr>

</tr>
</table>
</fieldset>
</form>

</section>

</body>
</html>