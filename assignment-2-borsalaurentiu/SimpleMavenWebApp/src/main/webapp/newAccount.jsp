<html>
<head> 
	<title>New account</title>
</head>
<body>
	<center><br><br><br><br><br>
		<table border = "1">
			<form action="NewAccountServlet" method="post">
				<tr>
					<td><center>Username</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td><center>Password</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><center>Credit</td>
					<td><input type="text" name="credit"></td>
				</tr>
				<tr>
					<td><center>Plan</td>
					<td>
						<input type="radio" name="paymentPlan" value="1"> 1 month<br>
  						<input type="radio" name="paymentPlan" value="6"> 6 months<br>
  						<input type="radio" name="paymentPlan" value="12"> 12 months</td>
				</tr>
		</table><br>
		<input type="submit" value="Create Account">
		</form><br><br>
		
		<center><a href="./">Login!</a></center>
	</center>
</body>
</html>