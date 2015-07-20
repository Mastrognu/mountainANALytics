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
			<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
			<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
			<script type="text/javascript">
			function clickButton(button){
				alert(name + " " + url);
				var name = $(button).data("name");
				var url = $(button).data("url");
				var lat = $(button).data("lat");
				var lon = $(button).data("lon");
				$.post("http://localhost:8080/MountainANALytics/selection", {mountainName: name, url: url, latitude: lat, longitude: lon});
				<!-- alert (url); -->
			}

			(function (){
				var map;
				var markerCount = 0;
				var markers = [
		   			<c:forEach items="${request.response}" var="photo">
						{name: "${photo.mountainName}", lat: ${photo.latitude}, lon: ${photo.longitude}, url: "${photo.url}"},
					</c:forEach>
				    ];

				function initialize() {
					var centerCoordinates = new google.maps.LatLng(${request.queryLatitude}, ${request.queryLongitude});
					var mapOptions = {
						center: centerCoordinates,
						zoom: 8,
						mapTypeId: google.maps.MapTypeId.SATELLITE
					};
					map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

					var centerMarker = new google.maps.Marker({
					      position: centerCoordinates,
					      map: map,
					      animation: google.maps.Animation.DROP,
					      icon: 'https://google-developers.appspot.com/maps/documentation/javascript/examples/full/images/beachflag.png',
					      title: '${request.queryName}'
					  });				
					var infowindow = new google.maps.InfoWindow({
					      content: "<h1>"+'${request.queryName}'+"</h1>"
					  });
					google.maps.event.addListener(centerMarker, 'click', function() {
					    infowindow.open(map,centerMarker);
					  });
					markers.forEach(function(marker){
						addMarkerToMap(marker);
					});
				}

				google.maps.event.addDomListener(window, 'load', initialize);

				function addMarkerToMap(marker){
				    var url = marker.url;
				    var name = marker.name;
				    var lat = marker.lat;
				    var lon = marker.lon;

				    var infowindow = new google.maps.InfoWindow();
				    var coordinates = new google.maps.LatLng(lat,lon);
				    var marker = new google.maps.Marker({
				        position: coordinates,
				        map: map,
				        animation: google.maps.Animation.DROP,
				        title: name
				});

			    //Gives each marker an Id for the on click
			    markerCount++;

			    //htmlMarkupForInfoWindow = "<img src=\""+image+"\" width=\"600px\" height=\"600px\"><br><button data-url=\"${url}\" onclick=\"clickButton(this)\">Save</button>"";
				var htmlMarkupForInfoWindow = "<center><h1>"+name+"</h1></center><br><img src="+url+" width=\"400px\" height=\"400px\"><br><button data-name=\""+name+"\" data-url=\""+url+"\" data-lat=\""+lat+"\" data-lon=\""+lon+"\" onclick=\"clickButton(this)\">Save</button>";
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