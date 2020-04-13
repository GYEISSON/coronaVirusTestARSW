package edu.eci.arsw.covid19arsw.controller;


import edu.eci.arsw.covid19arsw.Covid;
import edu.eci.arsw.covid19arsw.model.Country;
import edu.eci.arsw.covid19arsw.services.CoronaVirusStatException;
import edu.eci.arsw.covid19arsw.services.CoronaVirusStatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/covid19")
public class CoronaVirusStatController {

    @Autowired
    private CoronaVirusStatServices coronaVirusStatServices;

    @RequestMapping(value="/general-stats",method = RequestMethod.GET)
    public ResponseEntity<?> getGeneralStats() {
        try{
            List<Country> countries = coronaVirusStatServices.getTopStats();
            return new ResponseEntity<>(countries, HttpStatus.OK);
        }
        catch (CoronaVirusStatException covid){
            Logger.getLogger(Covid.class.getName()).log(Level.SEVERE, null, covid.noSeEncontraronDatos);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/total-statistics",method = RequestMethod.GET)
    public ResponseEntity<?> getTotalStatistics() {
            try{
                List<Country> total = coronaVirusStatServices.getTotalStatistics();
                return new ResponseEntity<>(total, HttpStatus.OK);
            }catch (CoronaVirusStatException covid){
                Logger.getLogger(Covid.class.getName()).log(Level.SEVERE, null, covid.noSeEncontraronDatos);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }
    @RequestMapping(value = "/all-stats",method = RequestMethod.GET)
    public ResponseEntity<?> getAllStats(){
        try{
            List<Country> countries = coronaVirusStatServices.getAllGeneralStats();
            return new ResponseEntity<>(countries, HttpStatus.OK);
        }catch (CoronaVirusStatException covid){
            Logger.getLogger(Covid.class.getName()).log(Level.SEVERE, null, covid.noSeEncontraronDatos);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(path = "/{country}",method = RequestMethod.GET)
    public ResponseEntity<?> getCountryStat(@PathVariable ("country") String countryName){
        try{
            //System.out.println(countryName);
            List<Country> country = coronaVirusStatServices.getCountryStats(countryName);
            //System.out.println(country.size());
            return new ResponseEntity<>(country, HttpStatus.OK);
        }catch (CoronaVirusStatException covid){
            Logger.getLogger(Covid.class.getName()).log(Level.SEVERE, null, covid.noSeEncontraronDatos);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
