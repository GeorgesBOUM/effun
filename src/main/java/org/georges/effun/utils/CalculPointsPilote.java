package org.georges.effun.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.georges.effun.model.GrandPrix;
import org.georges.effun.model.Pilote;
import org.georges.effun.model.ResultatPilote;

public class CalculPointsPilote {
    
    public static Map<Pilote, Integer> mappingPointsPilote(GrandPrix gp) {
	Map<Pilote, Integer> piloteEtPoints = new HashMap<>();
	List<ResultatPilote> resultatPilotes = gp.getListeResultatsTousPilotes();
	
	resultatPilotes.forEach(rp -> {piloteEtPoints.put(rp.getPilote(), getPointsPiloteSelonPlace(rp));});
	resultatPilotes.sort(new ComparaisonMeilleurTemps());
	ResultatPilote plusRapide = resultatPilotes.get(0);
	
	if (estDansLesDixPremiers(plusRapide)) {
	    piloteEtPoints.put(plusRapide.getPilote(), piloteEtPoints.get(plusRapide.getPilote()) + 1);
	}
	return piloteEtPoints;	
    }
    
    private static int getPointsPiloteSelonPlace(ResultatPilote rp) {
	Integer place = rp.getPlace();
	switch (place) {
	case 1:
	    return 25;
	case 2:
	    return 18;
	case 3:
	    return 15;
	case 4:
	    return 12;
	case 5:
	    return 10;
	case 6:
	    return 8;
	case 7:
	    return 6;
	case 8:
	    return 4;
	case 9:
	    return 2;
	case 10:
	    return 1;
	default:
	    return 0;
	}
    }
    
    private static boolean estDansLesDixPremiers(ResultatPilote rp) {
	return rp.getPlace() >= 1 && rp.getPlace() <= 10;
    }
}
