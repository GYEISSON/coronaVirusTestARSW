package edu.eci.arsw.covid19arsw.controller;


import edu.eci.arsw.covid19arsw.Covid19arsw;
import edu.eci.arsw.covid19arsw.model.Country;
import edu.eci.arsw.covid19arsw.services.CoronaVirusStatException;
import edu.eci.arsw.covid19arsw.services.CoronaVirusStatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        	System.out.println("solicitud realizada ");
            List<Country> countries = coronaVirusStatServices.getGeneralStats();
            Collections.sort(countries);
            return new ResponseEntity<>(countries, HttpStatus.OK);
        }
        catch (CoronaVirusStatException covid){
            Logger.getLogger(Covid19arsw.class.getName()).log(Level.SEVERE, null, covid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
