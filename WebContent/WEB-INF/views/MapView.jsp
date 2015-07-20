<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="US-ASCII"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
			<title>Results</title>
 			<style type="text/css">
				 html { height: 100% }
				 body { height: 100%; margin: 0; padding: 0 }
				 #map-canvas { height: 100% }
			</style>
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js" />
			<script>
				function clickButton(button){
					var photo = $(button).data("url");
					alert("Photo saved");
					$.post("http://localhost:8080/MountainANALytics/selection", {photo: photo});
					<!-- alert (url); -->
					}
			</script>

			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
			<script type="text/javascript">
		(function (){
			var map;
			var markerCount = 0;
			var markers = [
	   			<c:forEach items="${request.response}" var="photo">
					{name: "${photo.mountainName}", lat: ${photo.latitude}, lon: ${photo.longitude}, url: "${photo.url}"},
				</c:forEach>
			    ];

			function initialize() {
				var centerCoordinates = new google.maps.LatLng(46.351873, 10.556306);
				var mapOptions = {
					center: centerCoordinates,
					zoom: 6,
					mapTypeId: google.maps.MapTypeId.SATELLITE
				};
				map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
				markers.forEach(function(marker){
					addMarkerToMap(marker);
				});
			}
			google.maps.event.addDomListener(window, 'load', initialize);

			function addMarkerToMap(marker){
			    var infowindow = new google.maps.InfoWindow();
			    var coordinates = new google.maps.LatLng(marker.lat,marker.lon);
			    var url = marker.url;
			    var name = marker.name;
			    var marker = new google.maps.Marker({
			        position: coordinates,
			        map: map,
			        animation: google.maps.Animation.DROP,
			        title: name
			    });

			    //Gives each marker an Id for the on click
			    markerCount++;

			    //htmlMarkupForInfoWindow = "<img src=\""+image+"\" width=\"600px\" height=\"600px\"><br><button data-url=\"${url}\" onclick=\"clickButton(this)\">Save</button>"";
				var htmlMarkupForInfoWindow = "<h1>"+name+"</h1><br><img src="+url+" width=\"600px\" height=\"600px\"><br><button data-url="+marker+" onclick=\"clickButton(this)\">Save</button>";
			    //Creates the event listener for clicking the marker
			    //and places the marker on the map
			    google.maps.event.addListener(marker, 'click', (function(marker, markerCount) {
			        return function() {
			            infowindow.setContent(htmlMarkupForInfoWindow);
			            infowindow.open(map, marker);
			        }
			    })(marker, markerCount));  
			}
		}());
		</script>
		</head>
		<body>
		<div id="map-canvas" />

<%--
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

	<c:forTokens items="${request.response}" delims="," var="url">
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