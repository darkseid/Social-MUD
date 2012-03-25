<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login</title>
	</head>
	<body>

<div class="login">	
	<h1>Login</h1>
	
	<form action="/authenticate.do" method="POST">
		<label for="user">User:</label>
		<input type="text" name="user"/>
		<label for="password">password:</label>
		<input type="text" name="password"/>		
		<input type="submit" value="Login"/>
	</form>
</div>

<div class="newUser">

	<h1>Login</h1>
	
	<form action="/newUser.do" method="POST">
		<label for="userName">User name:</label>
		<input type="text" name="userName"/>
		<label for="password">User name:</label>
		<input type="password" name="password"/>		
		<input type="submit" value="Create"/>
	</form>
	
</div>

	</body>
</html>