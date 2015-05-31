<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Thanks</title>
</head>
<body>
	Your query is: ${request.query}.
	<br />
	<br />
	<c:out value="${request.response}" />
	<br />
	<br />
		<c:forTokens items="${request.response}" delims="," var="url">
    	<img src="<c:url value="${url}"/>"/>
	</c:forTokens>
</body>
</html>

