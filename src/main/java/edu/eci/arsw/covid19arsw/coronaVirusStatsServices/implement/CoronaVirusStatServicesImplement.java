package edu.eci.arsw.covid19arsw.coronaVirusStatsServices.implement;

import com.google.gson.reflect.TypeToken;

import edu.eci.arsw.covid19arsw.coronaVirusStatsCache.CoronavirusStatsCache;
import edu.eci.arsw.covid19arsw.coronaVirusStatsModel.Country;
import edu.eci.arsw.covid19arsw.coronaVirusStatsModel.Province;
import edu.eci.arsw.covid19arsw.coronaVirusStatsServices.CoronaVirusStatException;
import edu.eci.arsw.covid19arsw.coronaVirusStatsServices.CoronaVirusStatsServices;
import edu.eci.arsw.covid19arsw.httpConnectionService.HttpConnectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import java.util.*;

import org.json.JSONArray;


@Service
@Configuration
public class CoronaVirusStatServicesImplement implements CoronaVirusStatsServices{

	@Autowired
    HttpConnectionService httpConnectionService=null;
    @Autowired
    CoronavirusStatsCache cache =null;


    Timer timer = new Timer();

    TimerTask tarea = new TimerTask() {
        @Override
        public void run() {
            updateStats();
            System.out.println("data updated!");
        }
    };

    /**
     *
     * @throws CoronaVirusStatException
     * update the cache
     */
    @Override
    public void updateStats(){
        List<Country> listCountries = new ArrayList<Country>();
        JSONArray stats = httpConnectionService.HTTPConnection("");
        List<Province> listaProvinces = httpConnectionService.getGson()
                .fromJson(stats.toString (), new TypeToken<List<Province>>() {
                }.getType());

        HashMap<String, ArrayList<Integer>> countries = fillMap(listaProvinces);

        for (HashMap.Entry<String, ArrayList<Integer>> entry : countries.entrySet()) {
            listCountries.add(new Country(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2)));
        }
        cache.setLittleData(listCountries);
    }
    @Override
    public List<Country> getAllGeneralStats() throws CoronaVirusStatException {
        List<Country> listCountries = new ArrayList<Country>();
        if(cache.getCharged()){
            listCountries = cache.getAllData();
        }
        else {
            timer.schedule(tarea,0,300000);
            JSONArray stats = httpConnectionService.HTTPConnection("");
            List<Province> listaProvinces = httpConnectionService.getGson()
                    .fromJson(stats.toString (), new TypeToken<List<Province>>() {
                    }.getType());

            HashMap<String, ArrayList<Integer>> countries = fillMap(listaProvinces);

            for (HashMap.Entry<String, ArrayList<Integer>> entry : countries.entrySet()) {
                listCountries.add(new Country(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2)));
            }
            cache.setLittleData(listCountries);
        }
        listCountries = sortCountries(listCountries);
        
        return listCountries;
    }
    @Override
    public List<Country> getTopStats() throws CoronaVirusStatException{
        List<Country> countries = getAllGeneralStats();
        
        return countries.subList(0,10);
    }
    @Override
    public List<Country> getTotalStatistics() throws CoronaVirusStatException {
        List<Country> countries = getAllGeneralStats();
        List<Country> totalList = new ArrayList<Country>();
        Country totalCountry = new Country("Total",0,0,0);
        for(Country c :countries){
            totalCountry.setConfirmed( totalCountry.getConfirmed() + c.getConfirmed());
            totalCountry.setRecovered(totalCountry.getRecovered() + c.getRecovered());
            totalCountry.setDeaths(totalCountry.getDeaths() + c.getDeaths());
        }
        totalList.add(totalCountry);
        
        return totalList;
    }
    @Override
    public List<Country> getCountryStats(String countryName) throws CoronaVirusStatException {
        List<Country> listCountries = new ArrayList<Country>();
        if(cache.getCharged()){
            listCountries = cache.getCountry(countryName);
        }
        else {
            JSONArray stats = httpConnectionService.HTTPConnection(countryName);
            List<Province> listaProvinces = httpConnectionService.getGson()
                    .fromJson(stats.toString(), new TypeToken<List<Province>>() {
                    }.getType());
            HashMap<String, ArrayList<Integer>> countries = fillMap(listaProvinces);
            for (HashMap.Entry<String, ArrayList<Integer>> entry : countries.entrySet()) {
                System.out.println("one more time ");
                if(entry.getKey().toLowerCase().equals(countryName.toLowerCase())) {
                    listCountries.add(new Country(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1), entry.getValue().get(2)));
                }
            }
        }

        return listCountries;
    }
    @Override
    public HashMap<String,ArrayList<Integer>> fillMap(List<Province> provinces){
        HashMap<String, ArrayList<Integer>> countries=new HashMap<String, ArrayList<Integer>>();
        for(Province p: provinces){
            ArrayList<Integer> tempArray = new ArrayList<Integer>();
            if(!countries.containsKey(p.getCountry())){
                tempArray.add(p.getDeaths());
                tempArray.add(p.getConfirmed());
                tempArray.add(p.getRecovered());
                countries.put(p.getCountry(),tempArray);
            }else{
                ArrayList<Integer> aux = new ArrayList<Integer>();
                tempArray = countries.get(p.getCountry());
                countries.remove(p.getCountry());
                aux.add(tempArray.get(0)+p.getDeaths());
                aux.add(tempArray.get(1)+p.getConfirmed());
                aux.add(tempArray.get(2)+p.getRecovered());
                countries.put(p.getCountry(),aux);
            }
        }
        
        return countries;
    }
    @Override
    public List<Country> sortCountries( List<Country> countries){
        int swap=1;
        Country temporalCountry= null;
        while(swap>0) {
            swap=0;
            for (int i = 1; i < countries.size(); i++) {
                if (countries.get(i).getConfirmed() > countries.get(i - 1).getConfirmed()) {
                    temporalCountry = countries.get(i - 1);
                    countries.set(i - 1, countries.get(i));
                    countries.set(i, temporalCountry);
                    swap++;
                }
            }
        }
        
        return countries;
    }
}
