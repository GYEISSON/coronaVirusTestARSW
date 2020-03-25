package edu.eci.arsw.coronaVirus.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Service;

@Service
public class HttpConnectionService {

    public String getGeneralStats() throws UnirestException {
        String jsonString = null;
        HttpResponse<String> response = Unirest.get("https://covid-19-coronavirus-statistics.p.rapidapi.com/v1/stats")
                .header("x-rapidapi-host", "cometari-airportsfinder-v1.p.rapidapi.com")
                .header("x-rapidapi-key", "9077e18e69mshd0c95d063e40146p183591jsn9f9008035e02")
                .asString();
        jsonString = response.getBody();
        return jsonString;
    }
}

