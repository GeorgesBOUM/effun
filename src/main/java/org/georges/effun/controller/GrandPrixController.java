package org.georges.effun.controller;

import java.util.Map;

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
    
}
