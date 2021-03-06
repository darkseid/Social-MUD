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

	<div class="newUser">

		<h1>Register a new user</h1>
		
		<div class="formError">
			<c:forEach var="error" items="${errors}">
				<c:out value="${error.defaultMessage}"/>
			</c:forEach>
		</div>

		<form action="/newUser.do" method="POST">
			<label for="userName">User name:</label>
			<input type="text" name="userName" /><br/>
			<label for="password">Password:</label>
			<input type="password" name="password" /><br/>
			Choose your class:<br/>
			<c:forEach items="${classes}" var="class">
				<input type="radio" name="className" value="${class}"/>
				<label for="className">${class}</label><br />
			</c:forEach>
			<input type="submit" value="Register" />
		</form>

	</div>
</body>
</html>