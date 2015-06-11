<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
			<title>Results</title>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" />
			<script>
				function clickButton(button){
					var url = $(button).data("url");
					$.post("http://localhost:8080/MountainANALytics/selection", {url: url});
					alert (url);
					}
			</script>

			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=sensor=false"></script>
			<script type="text/javascript">
			function initialize() {
				var mapOptions = {
				center: { lat: -34.397, lng: 150.644},
				zoom: 8
				};
				var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
			}
			google.maps.event.addDomListener(window, 'load', initialize);
			</script>
			<style type="text/css">
				html, body, #map-canvas { height: 100%; margin: 0; padding: 0;}
			</style>
		</head>
		<body>
		<div id="map-canvas" />
		Your query is: ${request.query}.
		<br />
		<br />
		<c:out value="${request.response}" />
		<br />
		<br />
		<c:forTokens items="${request.response}" delims="," var="url">
			<img src="<c:url value="${url}"/>" /><br /> 
			<button data-url="${url}" onclick="clickButton(this)">Save</button>
			<br />
		</c:forTokens>

<%-- 	<c:forTokens items="${request.response}" delims="," var="url">
			<spring:url value="/selection" var="saveUrlFromForm" /> 
			<form action="${saveUrlFromForm}" method="post"> 
				<input type="hidden" name="url" value="${url}" />
				<br />  
				<img src="<c:url value="${url}"/>" /><br /> 
				<input type="submit" value="Save" /> 
			</form> 
			<br />
		</c:forTokens> --%>
	</body>
</html>							