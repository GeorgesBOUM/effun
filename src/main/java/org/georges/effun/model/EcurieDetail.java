package org.georges.effun.model;

import java.util.List;

import lombok.Data;

@Data
public class EcurieDetail {
    private Ecurie ecurie;
    List<Pilote> listePilotes;
    Integer points;
    Long nombreDeVictoires;
    
    public void ajoutPoints(int points) {
	this.points += points;
    }
    
    public void ajoutVictoires(Long victoires) {
	this.nombreDeVictoires += victoires;
    }
}
