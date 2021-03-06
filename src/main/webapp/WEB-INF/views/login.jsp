<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" type="text/css" href="../css/mud.css" />
</head>
<body>

	<div class="login">
		<h1>Login</h1>

		<div class="formError">
			<c:forEach var="error" items="${errors}">
				<c:out value="${error.defaultMessage}" />
			</c:forEach>
		</div>

		<form action="/authenticate.do" method="POST">
			<label for="user">User:</label> <input type="text" name="user" /> <label
				for="password">password:</label> <input type="password"
				name="password" /> <input type="submit" value="Login" />
		</form>
	</div>

	<a href="/register.do">Register new user</a>
	
</body>
</html>