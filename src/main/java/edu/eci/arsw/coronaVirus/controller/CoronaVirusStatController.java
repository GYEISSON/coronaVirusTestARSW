package edu.eci.arsw.coronaVirus.controller;

import edu.eci.arsw.coronaVirus.App;
import edu.eci.arsw.coronaVirus.model.Country;
import edu.eci.arsw.coronaVirus.services.CoronaVirusStatException;
import edu.eci.arsw.coronaVirus.services.CoronaVirusStatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
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
            List<Country> countries = coronaVirusStatServices.getGeneralStats();
            return new ResponseEntity<>(countries, HttpStatus.OK);
        }
        catch (CoronaVirusStatException covid){
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, covid);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
