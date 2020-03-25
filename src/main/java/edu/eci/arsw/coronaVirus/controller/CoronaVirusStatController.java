package edu.eci.arsw.coronaVirus.controller;

import edu.eci.arsw.coronaVirus.App;
import edu.eci.arsw.coronaVirus.services.CoronaVirusStatException;
import edu.eci.arsw.coronaVirus.services.CoronaVirusStatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.RequestWrapper;
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
            String airport = coronaVirusStatServices.getGeneralStats();
            return new ResponseEntity<>(airport, HttpStatus.OK);
        }
        catch (CoronaVirusStatException air){
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, air);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
