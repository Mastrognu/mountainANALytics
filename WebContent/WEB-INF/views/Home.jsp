<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert a query</title>
<link href="${pageContext.request.contextPath}/css/home_style.css"
	rel="stylesheet" type="text/css">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/image/favicon.ico"
	type="image/x-icon">
<link rel="icon"
	href="${pageContext.request.contextPath}/image/favicon.ico"
	type="image/x-icon">
</head>
<body>

	<div class="container">
		<img src="http://cliparts.co/cliparts/kTM/n87/kTMn87bEc.png" width="100%"> <br />
		<h1>Search for Alps localities</h1>
		<br />
		<spring:url value="/map" var="addQueryFromForm" />
		<form action="${addQueryFromForm}" method="post">
			<div class="group">
				<input type="text" name="query" required="" /> <span
					class="highlight"></span> <span class="bar"></span> <label>Search</label>
			</div>

			<div class="group">
				<input type="submit" value="Search" /> <span class="highlight"></span>
				<span class="bar"></span> <input type="reset" value="Clear" /> <span
					class="highlight"></span> <span class="bar"></span>
			</div>
		</form>

		<spring:url value="/photos" var="findPhotoByUser" />
		<form action="${findPhotoByUser}" method="post">
			<div class="group">
				<input type="submit" value="Retrieve your saved photos" /> <span
					class="highlight"></span> <span class="bar"></span>
			</div>
		</form>
	</div>

</body>
</html>