<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<style type="text/css">

p{
	color: red;
	font-size: 10px;
}

.inputform{
	float:left;
	width:400px;
	border:1px solid pink;
	border-radius:10px;
	padding:10px;
}
.inputform table tr td input,textarea,select{
	width:200px;
	}

#gender1,#gender2{width:10px;}

#submit{margin-left:100px;width:100px;}

#reset{width:100px;}

.design{background-color:red;color:white;padding-left:100px;}
</style>

</head>
<body>
<p>${msg}</p>
<!-- Header -->
<h2>Company Mailer..</h2>
<h3 style="background-color:red;color:red;">c</h3>

<!-- Registration Form -->
<div class="inputform">
	<h2 class="design">Registration Form</h2>
	
	<form action="register.do" method="post">
		<table>
		<tr>
			<td>Name:</td>
			<td><input type="text" name="name" required/></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" required/>@cmail.com</td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="text" name="password" required></td>
		</tr>
		<tr>
			<td>Gender:</td>
			<td><input id="gender1" type="radio" name="gender" value="male">Male<input id="gender2" type="radio" name="gender" value="female">Female</td>
		</tr>
		<tr><td>Date Of Birth:</td><td><input type="date" name="dob" required/></td></tr>
		<tr><td>AddressLine:</td><td><textarea name="addressLine" rows="5" cols="15"></textarea></td></tr>
		<tr><td>City:</td><td><input type="text" name="city"/ required></td></tr>
		<tr><td>State:</td><td><input type="text" name="state"/ required></td></tr>
		<tr><td>Country:</td>
			<td><select name="country" required>
					<option>Select-Country:</option>
					<option>India</option>
					<option>Pakistan</option>
					<option>China</option>
					<option>Bhutan</option>
					<option>USA</option>
					<option>France</option>
					<option>Other</option>
				</select></td></tr>
		<tr><td>Contact:</td><td><input type="text" name="contact" required/></td></tr>
		<tr><td colspan="2"><input id="submit" type="submit" value="register"/>
		<input id="reset" type="reset" value="clear"/>
		</td></tr>
		</table>
	
	
	
	</form>


</div>


<!-- Login Form -->
<div class="inputform" style="float:right;">
<h2 class="design">Login Form</h2>
<form action="login.do" method="post">
<table>
<tr><td>Email:</td><td><input type="email" name="email" required/></td></tr>
<tr><td>Password:</td><td><input type="password" name="password" required/></td></tr>
<tr><td colspan="2"><input id="submit" type="submit" value="login"/>
</td></tr>
</table>
</form>
</div>


</body>
</html>