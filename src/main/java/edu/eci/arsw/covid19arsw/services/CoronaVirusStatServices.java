package edu.eci.arsw.covid19arsw.services;

import com.google.gson.reflect.TypeToken;
import edu.eci.arsw.covid19arsw.model.Country;
import edu.eci.arsw.covid19arsw.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import java.lang.reflect.Array;
import java.util.*;

import org.json.JSONArray;


@Service
@Configuration
public class CoronaVirusStatServices {
    
	@Autowired
    HttpConnectionService httpConnectionService=null;


    public List<Country> getGeneralStats() throws CoronaVirusStatException {

        JSONArray stats = httpConnectionService.HTTPConnection("");
        List<Province> listaProvinces = httpConnectionService.getGson()
        													.fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
        HashMap<String, ArrayList<Integer>> countries=new HashMap<String, ArrayList<Integer>>();
        
        for(Province p: listaProvinces){
            //System.out.println( p.getCountry()  + " " + p.getDeaths()+ " "+p.getConfirmed() +" "+p.getRecovered() );
        	if( !countries.containsKey(p.getCountry()) ){
                
            	ArrayList<Integer> temp = new ArrayList<Integer>();
            	
                temp.add(p.getDeaths());
                temp.add(p.getConfirmed());
                temp.add(p.getRecovered());
                
                countries.put(p.getCountry(),temp);
            }else{
                //System.out.println("sumando");
            	ArrayList<Integer> aux = new ArrayList<Integer>();
                ArrayList<Integer> temp = countries.get(p.getCountry());
                
                countries.remove(p.getCountry());
                aux.add(temp.get(0)+p.getDeaths());
                aux.add(temp.get(1)+p.getConfirmed());
                aux.add(temp.get(2)+p.getRecovered());
                
                countries.put(p.getCountry(),aux);
            }
        }
        
        List<Country> listCountries = new ArrayList<Country>();

        for (HashMap.Entry<String, ArrayList<Integer>> entry : countries.entrySet()) {
            listCountries.add(new Country(entry.getKey(),entry.getValue().get(0),entry.getValue().get(1),entry.getValue().get(2)));
        }
        System.out.println(listCountries.get(0).getName());
        listCountries = sortCountries(listCountries);
        System.out.println(listCountries.get(0).getName());

        return listCountries;
    }

    private List<Country> sortCountries( List<Country> countries){
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
