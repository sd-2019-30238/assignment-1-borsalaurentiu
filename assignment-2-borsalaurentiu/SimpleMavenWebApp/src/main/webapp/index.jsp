<html>
<head> 
	<title>Login</title>
</head>
<body>
	<center><br><br><br><br><br>
		<table border = "1">
			<form action="LoginServlet" method="post">
				<tr>
					<td><center>Username </td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td><center>Password </td>
					<td><input type="password" name="password"></td>
				</tr>
		</table><br>
		<input type="submit" value="Login">
		</form><br><br>
		
		<center>Not registered? <a href="./newAccount.jsp">Create new account!</a></center>
	</center>
</body>
</html>
