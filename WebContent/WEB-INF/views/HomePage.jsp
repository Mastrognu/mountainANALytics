<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert a query</title>
</head>
<body>
	<spring:url value="/view" var="addQueryFromForm" />
	<form action="${addQueryFromForm}" method="post">
		Query: <input type="text" name="query" /><br /> 
		Please select a social network <br /> 
		Flickr: <input type="checkbox" name="flikrOk" />
		Panoramio: <input type="checkbox" name="panoramioOk" />
		<input type="submit" value="send" /> 
	</form>

</body>
</html>