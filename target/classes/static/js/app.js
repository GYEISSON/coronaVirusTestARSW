
var app = ( function() {

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

		// Initialize and add the map
		function initMap() {
		  // The location of Uluru
		  const uluru = { lat: -25.344, lng: 131.036 };
		  // The map, centered at Uluru
		  const map = new google.maps.Map(document.getElementById("map"), {
		    zoom: 4,
		    center: uluru,
		  });
		  // The marker, positioned at Uluru
		  const marker = new google.maps.Marker({
		    position: uluru,
		    map: map,
		  });
		}
		
		
	}

	return {
		topCountries:topCountries,
		allCountries:allCountries,
		mostrarCountry:mostrarCountry,
		totalStatistics:totalStatistics,
		createTable:createTable
	}


})();

app.topCountries();
