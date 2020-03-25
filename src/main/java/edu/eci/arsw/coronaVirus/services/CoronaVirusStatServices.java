package edu.eci.arsw.coronaVirus.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronaVirus.model.Country;
import edu.eci.arsw.coronaVirus.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


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

        return listCountries;
    }
}
