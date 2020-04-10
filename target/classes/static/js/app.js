
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
		var fila =$('#countriesRow');
		countries.map( function(element){
			//console.log(element);
			var markup = "<tr> <td>"+ element.name +"</td> <td>"+element.deaths+"</td> <td>"+ element.confirmed +"</td> <td>"+ element.recovered +"</td> </tr>";
			fila.append(markup);
			
		});
		console.log("datosMapeados");
	}
	function mostrarCountry(country) {
		console.log("buscando pais");
		$('#table tbody').empty();
		apiclient.getCountry(country);
	}
	return {
		topCountries:topCountries,
		allCountries:allCountries,
		mostrarCountry:mostrarCountry,
		createTable:createTable
	}
		
	
})();

app.topCountries();
