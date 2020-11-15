package edu.eci.arsw.covid19arsw.coronaVirusStatsServices;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import edu.eci.arsw.covid19arsw.coronaVirusStatsModel.Country;
import edu.eci.arsw.covid19arsw.coronaVirusStatsModel.Province;
public interface CoronaVirusStatsServices {
	abstract void updateStats();
	abstract List<Country> getAllGeneralStats() throws CoronaVirusStatException;
	abstract List<Country> getTopStats() throws CoronaVirusStatException;
	abstract List<Country> getTotalStatistics() throws CoronaVirusStatException;
	abstract List<Country> getCountryStats(String countryName) throws CoronaVirusStatException;
	abstract HashMap<String,ArrayList<Integer>> fillMap(List<Province> provinces);
	abstract List<Country> sortCountries( List<Country> countries);
}
