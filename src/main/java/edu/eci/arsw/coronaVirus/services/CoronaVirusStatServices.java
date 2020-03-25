package edu.eci.arsw.coronaVirus.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.eci.arsw.coronaVirus.model.Country;
import edu.eci.arsw.coronaVirus.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CoronaVirusStatServices {
    @Autowired
    HttpConnectionService httpConnectionService=null;


    public List<Country> getGeneralStats() throws CoronaVirusStatException {

        JSONArray stats = HttpConnectionService.HTTPConnection("");
        List<Province> listaProvinces = HttpConnectionService.getGson().fromJson(stats.toString(),new TypeToken<List<Province>>(){}.getType());
        HashMap<String, ArrayList<Integer>> countries = new HashMap<String, ArrayList<Integer>>();
        for(Province pr: listaProvinces){
            if(!countries.containsKey(pr.getCountry())){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(pr.getDeaths());temp.add(pr.getConfirmed());temp.add(pr.getRecovered());
                countries.put(pr.getCountry(),temp);
            }else{
                ArrayList<Integer> aux = new ArrayList<Integer>();
                ArrayList<Integer> temp = countries.get(pr.getCountry());
                countries.remove(pr.getCountry());
                aux.add(temp.get(0)+pr.getDeaths());aux.add(temp.get(1)+pr.getConfirmed());aux.add(temp.get(2)+pr.getRecovered());
                countries.put(pr.getCountry(),aux);
            }
        }
        List<Country> listCountries = new ArrayList<Country>();
        for (HashMap.Entry<String, ArrayList<Integer>> entry : countries.entrySet()) {
            listCountries.add(new Country(entry.getKey(),entry.getValue().get(0),entry.getValue().get(1),entry.getValue().get(2)));
        }

        return listCountries;
    }
}
