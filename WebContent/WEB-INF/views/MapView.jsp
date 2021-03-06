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
				var name = $(button).data("name");
				var url = $(button).data("url");
				var lat = $(button).data("lat");
				var lon = $(button).data("lon");
				alert("Photo of " + name + " saved!");
				$(button).prop('disabled', true);
				$.post("http://localhost:8080/MountainPhotoCollection/selection", {mountainName: name, url: url, latitude: lat, longitude: lon});
				<!-- alert (url); -->
			}

			(function (){
				var map;
				var markerCount = 0;
				var markers = [
		   			<c:forEach items="${model.response}" var="photo">
						{name: "${photo.mountainName}", lat: ${photo.latitude}, lon: ${photo.longitude}, url: "${photo.url}"},
					</c:forEach>
				    ];

				function initialize() {
					var centerCoordinates = new google.maps.LatLng(${model.queryLatitude}, ${model.queryLongitude});
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
					      title: '${model.queryName}',
					      zIndex: 10000
					  });
					var infowindow = new google.maps.InfoWindow({
					      content: "<h1>"+'${model.queryName}'+"</h1>"
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

				var htmlMarkupForInfoWindow = "<center><h1>"+name+"</h1></center><br><img src="+url+" ><br><button data-name=\""+name+"\" data-url=\""+url+"\" data-lat=\""+lat+"\" data-lon=\""+lon+"\" onclick=\"clickButton(this)\">Save</button>";
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
	</body>
</html>							