<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Results</title>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- <script>
$(document).ready(function(){
    $("button").click(function(){
        $.post("demo_test_post.asp",
        {
          name: "Donald Duck",
          city: "Duckburg"
        },
        function(data,status){
            alert("Data: " + data + "\nStatus: " + status);
        });
    });
});
</script> -->
</head>
<body>
	Your query is: ${request.query}.
	<br />
	<br />
	<c:out value="${request.response}" />
	<br />
	<br />
	<c:forTokens items="${request.response}" delims="," var="url">
		<spring:url value="/selection" var="saveUrlFromForm" />
		<form action="${saveUrlFromForm}" method="post">
			<input type="hidden" name="url" value="${url}" /><br /> <img
				src="<c:url value="${url}"/>" /><br /> <input type="submit"
				value="Save" />
		</form>
		<br />
	</c:forTokens>
</body>
</html>

