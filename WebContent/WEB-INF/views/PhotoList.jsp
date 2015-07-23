<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/home_style.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<center><h1>Your saved photos</h1></center>
	<br>
	<table>
		<c:forEach items="${model.response}" var="photo">
			<tr>
				<td align="middle"><h3>${photo.mountainName}:</h3></td><td> <img src="${photo.url}" /></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a href="javascript:history.back()"><h2>Go Back</h2></a>
</body>
</html>