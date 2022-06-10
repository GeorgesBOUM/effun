package org.georges.effun.controller;

import java.util.HashMap;
import java.util.Map;

import org.georges.effun.model.Ecurie;
import org.georges.effun.model.GrandPrix;
import org.georges.effun.model.Pilote;
import org.georges.effun.service.GrandPrixService;
import org.georges.effun.utils.CalculPointsPilote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrandPrixController {

    @Autowired
    private GrandPrixService gps;

    @GetMapping("/standings/pilotes/{annee}")
    public Map<Pilote, Integer> getClassementPilotesParAnnee(@PathVariable int annee) {
	
	GrandPrix grandPrix = gps.getGrandPrixParAnnee(annee)
				 .get(); // peut être catcher le NoSuchElementException ?
	
	return CalculPointsPilote.mappingPointsPilote(grandPrix);   
    } 
    
    @GetMapping("/standings/ecurie/{annee}")
    public Map<Ecurie, Integer> getClassementEcuriesParAnnee(@PathVariable int annee){
	Map<Ecurie, Integer> ecuriesEtPoints = new HashMap<>();
	this.getClassementPilotesParAnnee(annee)
	    .entrySet()
	    .stream()
	    .forEach(entry -> {Ecurie key = entry.getKey().getEcurie();
    	    		       if (ecuriesEtPoints.containsKey(key)) {
    	    			   ecuriesEtPoints.put(key, ecuriesEtPoints.get(key) + entry.getValue());			
    	    		       } else {
    	    			   ecuriesEtPoints.put(key, entry.getValue());
    	    		       }
	    		    });
	return ecuriesEtPoints;
    }
    
}
