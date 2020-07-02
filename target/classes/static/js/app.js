
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
			var markup = "<tr> <td>"+ element.name +"</td> <td>"+beautifulNumber(element.deaths)+"</td> <td>"+ beautifulNumber(element.confirmed) +"</td> <td>"+ beautifulNumber(element.recovered) +"</td> </tr>";
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
		console.log("length number"+ number.length)
		var contador = 0;
		for(i = size ; i>=0; i--){
			if( contador>0 && contador%3==0){
				newNumber = coma.concat(newNumber);
				console.log(newNumber+" coma number");
				coma = ",";

			}
			newNumber = number.substring(i,i+1).concat(newNumber);
			console.log(newNumber);
			contador++;
		}
		console.log(newNumber);
		return newNumber;

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
