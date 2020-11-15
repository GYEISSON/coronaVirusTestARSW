document.addEventListener('DOMContentLoaded', function () {
	if (document.querySelectorAll('#map').length > 0)
	{
	  if (document.querySelector('html').lang)
		lang = document.querySelector('html').lang;
	  else
		lang = 'en';
  
	  var js_file = document.createElement('script');
	  js_file.type = 'text/javascript';
	  js_file.src = 'https://maps.googleapis.com/maps/api/js?callback=initMap&signed_in=true&language=' + lang;
	  document.getElementsByTagName('head')[0].appendChild(js_file);
	}
  });
  
var app = ( function() {
	var map;
	var markers;
	var bounds;

	function initMap()
	{
		map = new google.maps.Map(document.getElementById('map'), {
			center: {lat:37.566, lng: 126.9784},
			zoom: 8
		});
		fetch('https://raw.githubusercontent.com/jayshields/google-maps-api-template/master/markers.json')
			.then(function(response){return response.json()})
			.then(plotMarkers);
	}
	function plotMarkers(m)
	{
	markers = [];
	bounds = new google.maps.LatLngBounds();

	m.forEach(function (marker) {
		var position = new google.maps.LatLng(marker.location.latitude, marker.location.longitude);

		markers.push(
		new google.maps.Marker({
			position: position,
			map: map,
			animation: google.maps.Animation.DROP
		})
		);

		bounds.extend(position);
	});

	map.fitBounds(bounds);
	}

	function topCountries() {

	    $('#table tbody').empty();
		apiclient.getCountries();
	}

	function  allCountries() {
		console.log("all countries");
		$('#table tbody').empty();
		apiclient.getAllCountries();
	}


	function createTable(countries){
		var ind = 0;
		var fila =$('#countriesRow');
		countries.map( function(element){
			//console.log(element);
			var markup = "<tr> <td>"+ ++ind +"</td> <td>"+ element.name +"</td> <td>"+beautifulNumber(element.deaths)+"</td> <td>"+ beautifulNumber(element.confirmed) +"</td> <td>"+ beautifulNumber(element.recovered) +"</td> </tr>";
			fila.append(markup);

		});
		console.log("datosMapeados");
	}
	function mostrarCountry(country) {
		console.log("buscando pais");
		$('#table tbody').empty();
		apiclient.getCountry(country);
	}

	function totalStatistics() {
		console.log("total estadisticas");
		$('#table tbody').empty();
		apiclient.getTotalStatistics();
	}

	function beautifulNumber(number){
		number = number.toString();
		var newNumber = "";
		const size = number.length - 1;
		var coma=",";
		var contador = 0;
		for(i = size ; i>=0; i--){
			if( contador>0 && contador%3==0){
				newNumber = coma.concat(newNumber);
				coma = ",";
			}
			newNumber = number.substring(i,i+1).concat(newNumber);
			contador++;
		}
		return newNumber;
	}

	return {
		initMap:initMap,
		plotMarkers:plotMarkers,
		topCountries:topCountries,
		allCountries:allCountries,
		mostrarCountry:mostrarCountry,
		totalStatistics:totalStatistics,
		createTable:createTable
	}


})();

app.topCountries();