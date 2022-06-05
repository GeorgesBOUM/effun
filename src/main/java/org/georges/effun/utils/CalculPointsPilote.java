package org.georges.effun.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.georges.effun.model.Pilote;
import org.georges.effun.model.ResultatGeneral;
import org.georges.effun.model.ResultatPilote;

public class CalculPointsPilote {
    
    public static void mappingPointsPilote(ResultatGeneral rg) {
	HashMap<Pilote, Integer> piloteEtPoints = new HashMap<Pilote, Integer>();
	ArrayList<ResultatPilote> resultatPilotes = rg.getListeResultatsTousPilotes();
	for (ResultatPilote rp : resultatPilotes) {
	    int points = getPointsPiloteSelonPlace(rp) + ajoutPointBonus(rg);
	    piloteEtPoints.put(rp.getPilote(), points);
	}
    }
    
    private static int getPointsPiloteSelonPlace(ResultatPilote rp) {
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
    
    private static boolean estDansLesDixPremiers(ResultatPilote rp) {
	return rp.getPlace() >= 1 && rp.getPlace() <= 10;
    }
    
    private static ResultatPilote piloteAvecMeilleurTemps(ResultatGeneral rg) {
	ArrayList<ResultatPilote> rgClasse =  rg.getListeResultatsTousPilotes();
	rgClasse.sort(new ComparaisonMeilleurTemps());
	return rgClasse.get(0);
    }
    private static int ajoutPointBonus(ResultatGeneral rg) {
	if (estDansLesDixPremiers(piloteAvecMeilleurTemps(rg))) {
	    return 1;
	} else {
	    return 0;
	}
    }
}
