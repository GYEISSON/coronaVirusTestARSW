package edu.eci.arsw.covid19arsw.CoronavirusStatsCache;

import edu.eci.arsw.covid19arsw.CoronavirusStatsModel.*;
import edu.eci.arsw.covid19arsw.CoronavirusStatsServices.CoronaVirusStatException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import java.util.HashMap;

@Service
public class CoronavirusStatsCache {

    private ConcurrentHashMap<String, Country> littleData;
    private List<Country> allCountries;
    private List<Country> responseList;
    private Boolean charged;

    public CoronavirusStatsCache(){
        littleData = new ConcurrentHashMap<String,Country>();
        allCountries = new ArrayList<>();
        responseList = new ArrayList<>();
        charged = false;
    }

    public void setLittleData( List<Country>  countries){
        for(Country c : countries){
            littleData.put(c.getName(),c);
        }
        this.allCountries = countries;
        System.out.println("cache actualizado");
        this.charged = true;
    }

    public List<Country> getCountry(String name) throws CoronaVirusStatException{
        responseList.clear();
        responseList.add(littleData.get(name));
        return responseList;
    }

    public List<Country> getAllData(){
        return allCountries;
    }

    public Boolean getCharged() {
        return charged;
    }

}
