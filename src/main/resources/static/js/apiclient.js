var api = ( function (){
	function getCountries(){
		axios({
			method:"get",
			url: "/covid19/general-stats"
		})
		.then(response => app.createTable(response.data))
		.catch(error => console.log(error));
	}
	
	return {
		getCountries:getCountries
	}	
})();