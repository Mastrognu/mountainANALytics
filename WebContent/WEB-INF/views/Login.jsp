<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/home_style.css" rel="stylesheet" type="text/css">
<title>Login</title>
</head>
<body>
	<div class="container">

		<spring:url value="/search" var="validateLogin" />
		<form action="${validateLogin}" method="post">
		<div class="group">
			<input type="text" name="email" required="" />
			<span class="highlight"></span>
			<span class="bar"></span> <label>	Insert you username</label>

			<input type="submit" value="Submit" />
			<span class="highlight"></span>
			<span class="bar"></span>
		</div>
		</form>
	</div>
</body>
</html>