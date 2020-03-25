package edu.eci.arsw.coronaVirus.services;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoronaVirusStatServices {
    @Autowired
    HttpConnectionService httpConnectionService=null;

    public String getGeneralStats() throws CoronaVirusStatException {
        String data = null;
        try {
            data = httpConnectionService.getGeneralStats();
        } catch(UnirestException ex) {
            throw new CoronaVirusStatException(CoronaVirusStatException.noSeEncontraronDatos);
        }
        return data;
    }
}
