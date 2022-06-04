package org.georges.effun.service;

import java.util.ArrayList;
import java.util.Optional;

import org.georges.effun.model.Pilote;
import org.georges.effun.model.ResultatGeneral;
import org.georges.effun.model.ResultatPilote;
import org.georges.effun.repository.ResultatGeneralRepository;
import org.georges.effun.utils.ComparaisonMeilleurTemps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
public class ResultatGeneralService {

    @Autowired
    private ResultatGeneralRepository rgr;

    public ArrayList<ResultatGeneral> getAllResultatsGeneraux() {
	return Lists.newArrayList(rgr.findAll());
    }

    public Optional<ResultatGeneral> getResultatGeneralParAnnee(int annee) {
	return this.getAllResultatsGeneraux()
		   .stream()
		   .filter(rgl -> rgl.getDate().getYear() == annee)
		   .findFirst();
    }

    public Pilote getPiloteAvecMeilleurTemps(ResultatGeneral rg) {
	ArrayList<ResultatPilote> rp = rg.getListeResultatsTousPilotes();
	rp.sort(new ComparaisonMeilleurTemps());
	return rp.get(0).getPilote();
    }
    
    private int calculPointsPiloteParPlace(ResultatPilote rp) {
	int points = 0;
	Integer place = rp.getPlace();
	switch (place) {
	case 1:
	    points = 25;
	    break;
	case 2:
	    points = 18;
	    break;
	case 3:
	    points = 15;
	    break;
	case 4:
	    points = 12;
	    break;
	case 5:
	    points = 10;
	    break;
	case 6:
	    points = 8;
	    break;
	case 7:
	    points = 6;
	    break;
	case 8:
	    points = 4;
	    break;
	case 9:
	    points = 2;
	    break;
	case 10:
	    points = 1;
	    break;
	default:
	    points = 0;
	}
	return points;
    }

}
