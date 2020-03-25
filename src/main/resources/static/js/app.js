

var app = ( function() {
	function mostrarTabla(){
       
	    $('#table tbody').empty();	    
		api.getCountries();
        
	}	
	
	function createTable(countries){
		var fila =$('#countriesRow');
		countries.map( function(element){
			console.log(element);
			var markup = "<tr> <td>"+ element.name +"</td> <td>"+element.deaths+"</td> <td>"+ element.infected +"</td> <td>"+ element.cured +"</td> </tr>";
			fila.append(markup);
		})
	}
	
	return {
		mostrarTabla:mostrarTabla,
		createTable:createTable
	}
		
	
})();

app.mostrarTabla();
console.log("FAIL");
console.log("FAIL");
console.log("FAIL");
console.log("FAIL");